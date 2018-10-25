package com.co.mundoviajero.business.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.business.SetDTOIntoEntities;
import com.co.mundoviajero.business.SetEntitiesIntoDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.event.CreateEventDTO;
import com.co.mundoviajero.dto.event.eventplace.CreateEventPlaceDTO;
import com.co.mundoviajero.dto.event.eventplace.EventPlaceResponseDTO;
import com.co.mundoviajero.dto.event.imageevent.ImageEventResponseDTO;
import com.co.mundoviajero.dto.event.EventResponseDTO;
import com.co.mundoviajero.persistence.dao.IEventDAO;
import com.co.mundoviajero.persistence.dao.IEventPlaceDAO;
import com.co.mundoviajero.persistence.dao.IImageEventDAO;
import com.co.mundoviajero.persistence.entity.Event;
import com.co.mundoviajero.persistence.entity.EventPlace;
import com.co.mundoviajero.persistence.entity.ImageEvent;
import com.co.mundoviajero.util.Constants;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;
import com.co.mundoviajero.util.exception.dto.ErrorDTO;

@Service
public class EventBusiness {

	@Autowired
	private MessageSourceAccessor messageSource;

	@Autowired
	private IEventDAO eventDAO;

	@Autowired
	private IEventPlaceDAO eventPlaceDAO;

	@Autowired
	private IImageEventDAO imageEventDAO;

	public ResponseEntity<ResponseDTO> getAllEvents() throws Exception {

		List<Event> events = eventDAO.getAllEvents();
		if (CollectionUtils.isNotEmpty(events)) {

			List<EventResponseDTO> eventsDTO = new ArrayList<>();
			events.forEach(event -> eventsDTO.add(SetEntitiesIntoDTO.setEventResponseDTO(event)));
			
			setListsInEvent(eventsDTO);

			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), eventsDTO),
					HttpStatus.OK);

		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseDTO> getEventsWithId(List<Long> eventsId) throws Exception {

		List<Event> events = eventDAO.getEventsWithId(eventsId);
		if (CollectionUtils.isNotEmpty(events)) {

			List<EventResponseDTO> eventsDTO = new ArrayList<>();
			events.forEach(event -> eventsDTO.add(SetEntitiesIntoDTO.setEventResponseDTO(event)));
			
			setListsInEvent(eventsDTO);

			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), eventsDTO),
					HttpStatus.OK);

		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseDTO> getEvent(Long id) throws ValidationException {

		StringBuilder sb = new StringBuilder(
				Validator.validateLong(id, FieldConstants.EVENT_ID, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"), sb.toString()));
		}

		Event event = eventDAO.getEvent(id);
		
		if (event != null) {
			
			EventResponseDTO eventResponseDTO = SetEntitiesIntoDTO.setEventResponseDTO(event);
			eventResponseDTO.setPlaces(setPlacesInEventDTO(eventResponseDTO.getId()));
			eventResponseDTO.setImages(setImagesInEventDTO(eventResponseDTO.getId()));
			
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					eventResponseDTO), HttpStatus.OK);
		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("GET_DESC_ERROR_EVENT")));

	}

	public ResponseEntity<ResponseDTO> getEventWithParameters(Map<String, Object> parameters)
			throws ValidationException {

		if (!parameters.isEmpty()) {

			List<Event> events = eventDAO.getEventWithParameters(parameters);
			if (CollectionUtils.isNotEmpty(events)) {

				List<EventResponseDTO> eventsDTO = new ArrayList<>();
				events.forEach(event -> eventsDTO.add(SetEntitiesIntoDTO.setEventResponseDTO(event)));
				
				setListsInEvent(eventsDTO);

				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
						eventsDTO), HttpStatus.OK);

			}
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
					HttpStatus.NOT_FOUND);
		}

		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_BODY_PARAMS")));

	}

	public ResponseEntity<ResponseDTO> createEvent(CreateEventDTO event) throws ValidationException {

		if (event != null) {
			if (!event.getPlaces().isEmpty()) {

				if (!Validator.validateDate(LocalDateTime.now().toString().replace("T", " "), event.getStartDate(),
						Constants.EVENT_CREATED_DATE)) {
					throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
							messageSource.getMessage("EVENT_INITIAL_HOUR")));
				}

				if (!Validator.validateDate(event.getStartDate(), event.getEndDate(), Constants.EVENT_DURATION)) {
					throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
							messageSource.getMessage("EVENT_FINAL_HOUR")));
				}

				for (CreateEventPlaceDTO evDTO : event.getPlaces()) {

					if (!Validator.validateDate(event.getStartDate(), evDTO.getEventPlaceStartDate(),
							Constants.EMPTY)) {
						throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
								messageSource.getMessage("EVENT_PLACE_INITIAL_HOUR")));
					}

					if (!Validator.validateDate(evDTO.getEventPlaceStartDate(), evDTO.getEventPlaceEndDate(),
							Constants.EVENT_DURATION)) {
						throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
								messageSource.getMessage("EVENT_PLACE_PARTIAL_FINAL_HOUR")));
					}

					if (!Validator.validateDate(evDTO.getEventPlaceEndDate(), event.getEndDate(), Constants.EMPTY)) {
						throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
								messageSource.getMessage("EVENT_PLACE_FINAL_HOUR")));
					}

				}

				if (eventDAO.validResponsible(Long.parseLong(event.getPersonIdResponsible()))) {

					Long eventId = eventDAO.createEvent(SetDTOIntoEntities.setEvent(event));

					if (eventId != -1) {

						List<EventPlace> places = SetDTOIntoEntities.setEventPlace(event.getPlaces(), eventId);

						if (!eventPlaceDAO.createEventPlaces(places)) {
							throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
									messageSource.getMessage("FAIL_CREATED_EVENT_PLACE")));
						}

						List<ImageEvent> images = SetDTOIntoEntities.setImageEvent(event.getImages(), eventId);

						if (!imageEventDAO.createImageEvent(images)) {
							throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
									messageSource.getMessage("FAIL_UPLOAD_EVENT_IMAGE")));
						}

						return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
								messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("POST_DESC_SUCCESS"),
								true), HttpStatus.OK);
					}
					throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
							messageSource.getMessage("FAIL_CREATED_EVENT")));
				}
				throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
						messageSource.getMessage("EVENT_INVALID_RESPONSIBLE")));

			}
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("NULL_EVENT_PLACES_FOR_EVENT")));
		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_BODY_PARAMS")));

	}

	public ResponseEntity<ResponseDTO> updateEvent(Map<String, String> bodyParameters) throws ValidationException {

		Long identifier;
		StringBuilder sb = new StringBuilder();

		if (!bodyParameters.isEmpty()) {

			if (bodyParameters.containsKey(FieldConstants.ID)) {

				identifier = Long.parseLong(bodyParameters.get(FieldConstants.ID));
				bodyParameters.remove(FieldConstants.ID);

				if (!bodyParameters.isEmpty()) {

					for (String parameter : bodyParameters.keySet()) {
						switch (parameter) {

						case FieldConstants.EVENT_NAME:
							sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENT_NAME,
									FieldConstants.EVENT_NAME_LENGTH, FieldConstants.EVENT_NAME_OBLIGATORY));
							break;

						case FieldConstants.EVENT_DESCRIPTION:
							sb.append(Validator.valideString(bodyParameters.get(parameter),
									FieldConstants.EVENT_DESCRIPTION, FieldConstants.EVENT_DESCRIPTION_LENGTH,
									FieldConstants.EVENT_DESCRIPTION_OBLIGATORY));
							break;

						case FieldConstants.EVENT_STARTDATE:
							sb.append(Validator.valideString(bodyParameters.get(parameter),
									FieldConstants.EVENT_STARTDATE, FieldConstants.EVENT_STARTDATE_LENGTH,
									FieldConstants.EVENT_STARTDATE_OBLIGATORY));
							break;

						case FieldConstants.EVENT_ENDDATE:
							sb.append(Validator.valideString(bodyParameters.get(parameter),
									FieldConstants.EVENT_ENDDATE, FieldConstants.EVENT_ENDDATE_LENGTH,
									FieldConstants.EVENT_ENDDATE_OBLIGATORY));
							break;

						case FieldConstants.EVENT_LONGITUDEMEETINGPOINT:
							sb.append(Validator.valideString(bodyParameters.get(parameter),
									FieldConstants.EVENT_LONGITUDEMEETINGPOINT, FieldConstants.LONGITUDE_LENGTH,
									FieldConstants.LONGITUDE_OBLIGATORY));
							break;

						case FieldConstants.EVENT_LATITUDEMEETINGPOINT:
							sb.append(Validator.valideString(bodyParameters.get(parameter),
									FieldConstants.EVENT_LATITUDEMEETINGPOINT, FieldConstants.LATITUDE_LENGTH,
									FieldConstants.LATITUDE_OBLIGATORY));
							break;

						case FieldConstants.EVENT_CAPACITY:
							sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)),
									FieldConstants.EVENT_CAPACITY, FieldConstants.EVENT_CAPACITY_LENGTH,
									FieldConstants.EVENT_CAPACITY_OBLIGATORY));
							break;

						case FieldConstants.EVENT_FARE:
							sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)),
									FieldConstants.EVENT_FARE, FieldConstants.EVENT_FARE_LENGTH,
									FieldConstants.EVENT_FARE_OBLIGATORY));
							break;

						case FieldConstants.EVENT_PERSONIDRESPONSIBLE:
							sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)),
									FieldConstants.EVENT_PERSONIDRESPONSIBLE, FieldConstants.ID_LENGTH,
									FieldConstants.ID_OBLIGATORY));
							break;
						case FieldConstants.STATE_ID:
							sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)),
									FieldConstants.STATE_ID, FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));
							break;
						default:
							break;
						}
					}

					if (sb.toString().length() > 0) {
						throw new ValidationException(
								new ErrorDTO(messageSource.getMessage("CODE_ERR"), sb.toString()));
					}

					if (eventDAO.updateEvent(bodyParameters, identifier)) {
						return new ResponseEntity<>(
								new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
										messageSource.getMessage("DESC_SUCCESS"),
										messageSource.getMessage("PUT_DESC_SUCCESS"), true),
								HttpStatus.PRECONDITION_REQUIRED);
					}

					return new ResponseEntity<>(
							new ResponseDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("DESC_ERR"),
									messageSource.getMessage("GET_DESC_ERROR"), null),
							HttpStatus.PRECONDITION_REQUIRED);

				}
				throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
						messageSource.getMessage("UPDATE_EVENT_PLACE_MORE_EXPECTED_PARAMS")));
			}
			throw new ValidationException(
					new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_EVENT_ID")));

		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_BODY_PARAMS")));

	}
	
	private void setListsInEvent(List<EventResponseDTO> eventsDTO) {
		
		eventsDTO.forEach(event -> {
			
			event.setPlaces(setPlacesInEventDTO(event.getId()));
			event.setImages(setImagesInEventDTO(event.getId()));
			
		});
		
	}
	
	private List<EventPlaceResponseDTO> setPlacesInEventDTO(Long eventId) {
		
		List<EventPlace> places = eventPlaceDAO.getAllEventPlaces(eventId);
		List<EventPlaceResponseDTO> placesDTO = new ArrayList<>();
		places.forEach(place -> placesDTO.add(SetEntitiesIntoDTO.setEventPlaceDTO(place)));
		
		return placesDTO;
		
	}
	
	private List<ImageEventResponseDTO> setImagesInEventDTO(Long eventId) {
		
		List<ImageEvent> images = imageEventDAO.getAllImageEvent(eventId);
		List<ImageEventResponseDTO> imagesDTO = new ArrayList<>();
		images.forEach(image -> imagesDTO.add(SetEntitiesIntoDTO.setImageEventDTO(image)));
		
		return imagesDTO;
	}

}