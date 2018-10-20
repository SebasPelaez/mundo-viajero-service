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

import com.co.mundoviajero.business.SetEntitiesIntoDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.event.CreateEventDTO;
import com.co.mundoviajero.dto.event.eventplace.CreateEventPlaceDTO;
import com.co.mundoviajero.dto.state.StateResponseDTO;
import com.co.mundoviajero.dto.event.EventResponseDTO;
import com.co.mundoviajero.persistence.dao.IEventDAO;
import com.co.mundoviajero.persistence.entity.Event;
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

	public ResponseEntity<ResponseDTO> getAllEvents() throws Exception {

		List<Event> events = eventDAO.getAllEvents();
		if (CollectionUtils.isNotEmpty(events)) {

			List<EventResponseDTO> eventsDTO = new ArrayList<>();
			events.forEach(event -> eventsDTO.add(SetEntitiesIntoDTO.setEventResponseDTO(event)));

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
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					SetEntitiesIntoDTO.setEventResponseDTO(event)), HttpStatus.OK);
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

				StringBuilder sb = new StringBuilder();

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

					sb.append(Validator.validateNumber(String.valueOf(evDTO.getCityId()), FieldConstants.CITY_ID,
							FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));

					sb.append(Validator.valideString(evDTO.getEventPlaceStartDate(), FieldConstants.EVENT_STARTDATE,
							FieldConstants.EVENT_STARTDATE_LENGTH, FieldConstants.EVENT_STARTDATE_OBLIGATORY));

					sb.append(Validator.valideString(evDTO.getEventPlaceEndDate(), FieldConstants.EVENT_ENDDATE,
							FieldConstants.EVENT_ENDDATE_LENGTH, FieldConstants.EVENT_ENDDATE_OBLIGATORY));

					sb.append(Validator.valideString(evDTO.getLongitudeEventPlace(), FieldConstants.EVENTPLACE_ALTITUDE,
							FieldConstants.LONGITUDE_LENGTH, FieldConstants.LONGITUDE_OBLIGATORY));

					sb.append(Validator.valideString(evDTO.getLatitudeEventPlace(), FieldConstants.EVENTPLACE_LATITUDE,
							FieldConstants.LONGITUDE_LENGTH, FieldConstants.LONGITUDE_OBLIGATORY));

				}
				
				for (String ieDTO : event.getImages()) {

					sb.append(Validator.valideString(ieDTO, FieldConstants.IMAGE_EVENT_PATH,
							FieldConstants.IMAGE_EVENT_PATH_LENGTH, FieldConstants.IMAGE_EVENT_PATH_OBLIGATORY));
				}

				if (sb.toString().length() > 0) {
					throw new ValidationException(
							new ErrorDTO(messageSource.getMessage("CODE_ERR"), sb.toString()));
				}

				if (eventDAO.validResponsible(Long.parseLong(event.getPersonIdResponsible()))) {
					String result = eventDAO.createEvent(event);
					if (result.equals(Constants.EMPTY)) {
						return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
								messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("POST_DESC_SUCCESS"), true),
								HttpStatus.OK);
					}
					throw new ValidationException(result);
				}
				throw new ValidationException(
						new ErrorDTO(messageSource.getMessage("EVENT_INVALID_RESPONSIBLE"), sb.toString()));

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
						sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENT_STARTDATE,
								FieldConstants.EVENT_STARTDATE_LENGTH, FieldConstants.EVENT_STARTDATE_OBLIGATORY));
						break;

					case FieldConstants.EVENT_ENDDATE:
						sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENT_ENDDATE,
								FieldConstants.EVENT_ENDDATE_LENGTH, FieldConstants.EVENT_ENDDATE_OBLIGATORY));
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
					case FieldConstants.STATEID:
						sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)),
								FieldConstants.STATEID, FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));
						break;
					default:
						break;
					}
				}

				if (sb.toString().length() > 0) {
					throw new ValidationException(sb.toString());
				}

				if (eventDAO.updateEvent(bodyParameters, identifier)) {
					return new ResponseEntity<>(
							new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
									messageSource.getMessage("DESC_SUCCESS"),
									messageSource.getMessage("PUT_DESC_SUCCESS"), true),
							HttpStatus.PRECONDITION_REQUIRED);
				}

				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
						messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
						HttpStatus.PRECONDITION_REQUIRED);

			}
			throw new ValidationException(messageSource.getMessage("UPDATE_EVENT_PLACE_MORE_EXPECTED_PARAMS"));

		} else {
			throw new ValidationException(messageSource.getMessage("MISS_EVENT_ID"));
		}

	}

}