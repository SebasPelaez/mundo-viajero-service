package com.co.mundoviajero.business.Event;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.EventDTO;
import com.co.mundoviajero.dto.EventPlaceDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.persistence.dao.IEventDAO;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;

@Service
public class EventBusiness {

	@Autowired
	private MessageSourceAccessor messageSource;

	@Autowired
	private IEventDAO eventDAO;
	
	public ResponseEntity<ResponseDTO> getAllEvents() throws Exception {
		List<EventDTO> events = eventDAO.getAllEvents();
		if (events != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), events),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseDTO> getEvent(Long id) throws ValidationException {

		EventDTO eventDTO = eventDAO.getEvent(id);

		if (eventDTO != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), eventDTO),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseDTO> getEventWithParameters(Map<String, Object> parameters)
			throws ValidationException {

		List<EventDTO> eventDTO = eventDAO.getEventWithParameters(parameters);

		if (eventDTO != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), eventDTO),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseDTO> createEvent(EventDTO event) throws ValidationException {
		StringBuilder sb = new StringBuilder();

		if (parametersValidation(event) && !event.getPlaces().isEmpty()) {

			for (EventPlaceDTO evDTO : event.getPlaces()) {

				sb.append(Validator.validateNumber(String.valueOf(evDTO.getCityId()), FieldConstants.CITY_ID,
						FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));

				sb.append(Validator.valideString(evDTO.getEventPlaceStartDate(), FieldConstants.EVENT_STARTDATE,
						FieldConstants.EVENT_STARTDATE_LENGTH, FieldConstants.EVENT_STARTDATE_OBLIGATORY));

				sb.append(Validator.valideString(evDTO.getEventPlaceEndDate(), FieldConstants.EVENT_ENDDATE,
						FieldConstants.EVENT_ENDDATE_LENGTH, FieldConstants.EVENT_ENDDATE_OBLIGATORY));

				sb.append(Validator.valideString(evDTO.getAltitudeEventPlace(), FieldConstants.EVENTPLACE_ALTITUDE,
						FieldConstants.ALTITUDE_LENGTH, FieldConstants.ALTITUDE_OBLIGATORY));

				sb.append(Validator.valideString(evDTO.getLatitudeEventPlace(), FieldConstants.EVENTPLACE_LATITUDE,
						FieldConstants.ALTITUDE_LENGTH, FieldConstants.ALTITUDE_OBLIGATORY));

			}

			if (sb.toString().length() > 0) {
				throw new ValidationException(sb.toString());
			}

			EventDTO eventCreated = eventDAO.createEvent(event);
			if (eventCreated != null) {
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("POST_DESC_SUCCESS"),
						eventCreated), HttpStatus.OK);
			}
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("DESC_ERR"), messageSource.getMessage("POST_DESC_ERROR"), null),
					HttpStatus.PRECONDITION_REQUIRED);
		}

		throw new ValidationException("No hay lugares asociados");
	}

	public ResponseEntity<ResponseDTO> updateEvent(Map<String, String> bodyParameters) throws ValidationException {

		Long identifier;
		StringBuilder sb = new StringBuilder();

		if (bodyParameters.containsKey("id")) {

			identifier = Long.parseLong(bodyParameters.get("id"));
			bodyParameters.remove("id");

			for (String parameter : bodyParameters.keySet()) {
				switch (parameter) {

				case FieldConstants.EVENT_NAME:
					sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENT_NAME,
							FieldConstants.EVENT_NAME_LENGTH, FieldConstants.EVENT_NAME_OBLIGATORY));
					break;

				case FieldConstants.EVENT_DESCRIPTION:
					sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENT_DESCRIPTION,
							FieldConstants.EVENT_DESCRIPTION_LENGTH, FieldConstants.EVENT_DESCRIPTION_OBLIGATORY));
					break;

				case FieldConstants.EVENT_STARTDATE:
					sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENT_STARTDATE,
							FieldConstants.EVENT_STARTDATE_LENGTH, FieldConstants.EVENT_STARTDATE_OBLIGATORY));
					break;

				case FieldConstants.EVENT_ENDDATE:
					sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENT_ENDDATE,
							FieldConstants.EVENT_ENDDATE_LENGTH, FieldConstants.EVENT_ENDDATE_OBLIGATORY));
					break;

				case FieldConstants.EVENT_ALTITUDEMEETINGPOINT:
					sb.append(Validator.valideString(bodyParameters.get(parameter),
							FieldConstants.EVENT_ALTITUDEMEETINGPOINT, FieldConstants.ALTITUDE_LENGTH,
							FieldConstants.ALTITUDE_OBLIGATORY));
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
					sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)), FieldConstants.EVENT_FARE,
							FieldConstants.EVENT_FARE_LENGTH, FieldConstants.EVENT_FARE_OBLIGATORY));
					break;

				case FieldConstants.EVENT_PERSONIDRESPONSIBLE:
					sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)),
							FieldConstants.EVENT_PERSONIDRESPONSIBLE, FieldConstants.ID_LENGTH,
							FieldConstants.ID_OBLIGATORY));
					break;
				case FieldConstants.STATEID:
					sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)), FieldConstants.STATEID,
							FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));
					break;
				default:
					break;
				}
			}
			
			if (sb.toString().length() > 0) {
				throw new ValidationException(sb.toString());
			}
			
			if( eventDAO.updateEvent(bodyParameters, identifier) ) {		
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("PUT_DESC_SUCCESS"), null),
						HttpStatus.PRECONDITION_REQUIRED);			
			}
			
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
					HttpStatus.PRECONDITION_REQUIRED);

		}else {
			throw new ValidationException("Falta id del Evento");
		}

	}

	private boolean parametersValidation(EventDTO event) {
		StringBuilder sb = new StringBuilder();

		sb.append(Validator.valideString(event.getName(), FieldConstants.EVENT_NAME, FieldConstants.EVENT_NAME_LENGTH,
				FieldConstants.EVENT_NAME_OBLIGATORY));

		sb.append(Validator.valideString(event.getDescription(), FieldConstants.EVENT_DESCRIPTION,
				FieldConstants.EVENT_DESCRIPTION_LENGTH, FieldConstants.EVENT_DESCRIPTION_OBLIGATORY));

		sb.append(Validator.valideString(event.getStartDate(), FieldConstants.EVENT_STARTDATE,
				FieldConstants.EVENT_STARTDATE_LENGTH, FieldConstants.EVENT_STARTDATE_OBLIGATORY));

		sb.append(Validator.valideString(event.getEndDate(), FieldConstants.EVENT_ENDDATE,
				FieldConstants.EVENT_ENDDATE_LENGTH, FieldConstants.EVENT_ENDDATE_OBLIGATORY));

		sb.append(Validator.valideString(event.getAltitudeMeetingPoint(), FieldConstants.EVENT_ALTITUDEMEETINGPOINT,
				FieldConstants.ALTITUDE_LENGTH, FieldConstants.ALTITUDE_OBLIGATORY));

		sb.append(Validator.valideString(event.getLatitudeMeetingPoint(), FieldConstants.EVENT_LATITUDEMEETINGPOINT,
				FieldConstants.LATITUDE_LENGTH, FieldConstants.LATITUDE_OBLIGATORY));

		sb.append(Validator.validateNumber(String.valueOf(event.getCapaciticy()), FieldConstants.EVENT_CAPACITY,
				FieldConstants.EVENT_CAPACITY_LENGTH, FieldConstants.EVENT_CAPACITY_OBLIGATORY));

		sb.append(Validator.validateNumber(String.valueOf(event.getFare()), FieldConstants.EVENT_FARE,
				FieldConstants.EVENT_FARE_LENGTH, FieldConstants.EVENT_FARE_OBLIGATORY));

		sb.append(Validator.validateNumber(String.valueOf(event.getPersonIdResponsible()),
				FieldConstants.EVENT_PERSONIDRESPONSIBLE, FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));

		sb.append(Validator.validateNumber(String.valueOf(event.getStateId()), FieldConstants.STATEID,
				FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			return false;
		}
		return true;

	}

}