package com.co.mundoviajero.business.EventPlace;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.event.eventplace.EventPlaceDTO;
import com.co.mundoviajero.persistence.dao.IEventPlaceDAO;
import com.co.mundoviajero.util.BoundingBoxDistance;
import com.co.mundoviajero.util.Constants;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.BoundingBoxDistance.BoundingBox;
import com.co.mundoviajero.util.BoundingBoxDistance.MapPoint;
import com.co.mundoviajero.util.exception.ValidationException;

@Service
public class EventPlaceBusiness {

	@Autowired
	private MessageSourceAccessor messageSource;

	@Autowired
	private IEventPlaceDAO eventPlaceDAO;

	public ResponseEntity<ResponseDTO> getEventPlace(Long id) throws ValidationException {

		EventPlaceDTO eventDTO = eventPlaceDAO.getEventPlace(id);

		if (eventDTO != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), eventDTO),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseDTO> getAllEventPlacesForEvent(Long id) {
		List<EventPlaceDTO> events = eventPlaceDAO.getAllEventPlaces(id);
		if (events != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), events),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseDTO> updateEventPlace(Map<String, String> bodyParameters) throws ValidationException {

		Long identifier;
		StringBuilder sb = new StringBuilder();

		if (bodyParameters.containsKey(FieldConstants.ID)) {

			identifier = Long.parseLong(bodyParameters.get(FieldConstants.ID));
			bodyParameters.remove(FieldConstants.ID);

			if(!bodyParameters.isEmpty()){

				for (String parameter : bodyParameters.keySet()) {
					switch (parameter) {

						case FieldConstants.CITY_ID:
							sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)),
									FieldConstants.CITY_ID, FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));
							break;

						case FieldConstants.EVENT_STARTDATE:
							sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENT_STARTDATE,
									FieldConstants.EVENT_STARTDATE_LENGTH, FieldConstants.EVENT_STARTDATE_OBLIGATORY));
							break;

						case FieldConstants.EVENT_ENDDATE:
							sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENT_ENDDATE,
									FieldConstants.EVENT_ENDDATE_LENGTH, FieldConstants.EVENT_ENDDATE_OBLIGATORY));
							break;

						case FieldConstants.EVENTPLACE_ALTITUDE:
							sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENTPLACE_ALTITUDE,
									FieldConstants.LONGITUDE_LENGTH, FieldConstants.LONGITUDE_OBLIGATORY));
							break;

						case FieldConstants.EVENTPLACE_LATITUDE:
							sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.EVENTPLACE_LATITUDE,
									FieldConstants.LATITUDE_LENGTH, FieldConstants.LATITUDE_OBLIGATORY));
							break;
						default:
							break;
					}
				}

				if (sb.toString().length() > 0) {
					throw new ValidationException(sb.toString());
				}

				if (eventPlaceDAO.updateEventPlace(bodyParameters, identifier)) {
					return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
							messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("PUT_DESC_SUCCESS"), null),
							HttpStatus.OK);
				}

				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
						messageSource.getMessage("DESC_ERR"), messageSource.getMessage("PUT_DESC_ERROR"), null),
						HttpStatus.PRECONDITION_REQUIRED);
			}
			throw new ValidationException(messageSource.getMessage("UPDATE_EVENT_MORE_EXPECTED_PARAMS"));

		} else {
			throw new ValidationException(messageSource.getMessage("MISS_EVENT_PLACE_ID"));
		}

	}

	public List<Long> findNearestEvents(Map<String, String> parameters) throws ValidationException {

		double latitude = Double.parseDouble(parameters.get(Constants.LATITUDE));
		double longitude = Double.parseDouble(parameters.get(Constants.LONGITUDE));
		double halfSideInKm = Double.parseDouble(Constants.BOUNDINGBOXDISTANCE);

		MapPoint mp1 = new MapPoint(latitude, longitude);
		BoundingBox boundingBox = BoundingBoxDistance.GetBoundingBox(mp1, halfSideInKm);
		
		List<Long> eventsId = eventPlaceDAO.findNearestEvents(boundingBox);

		if(eventsId != null) {
			return eventsId;
		}

		throw new ValidationException(messageSource.getMessage("NEAREST_EVENTS_NOT_FOUND"));
		
	}

}
