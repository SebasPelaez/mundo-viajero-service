package com.co.mundoviajero.business.EventRecomendation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.business.SetDTOIntoEntities;
import com.co.mundoviajero.business.SetEntitiesIntoDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.EventRecomendation.CreateEventRecomendationDTO;
import com.co.mundoviajero.dto.EventRecomendation.EventRecomendationResponseDTO;
import com.co.mundoviajero.persistence.dao.IEventRecomendationDAO;
import com.co.mundoviajero.persistence.entity.EventRecomendation;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;
import com.co.mundoviajero.util.exception.dto.ErrorDTO;

@Service
public class EventRecomendationBusiness {

	@Autowired
	private MessageSourceAccessor messageSource;

	@Autowired
	private IEventRecomendationDAO eventRecomendationDAO;

	public ResponseEntity<ResponseDTO> getEventRecomendations(Long id) throws Exception {

		StringBuilder sb = new StringBuilder(
				Validator.validateLong(id, FieldConstants.EVENT_RECOMENDATION_ID, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"), sb.toString()));
		}

		List<EventRecomendation> eventRecomendations = eventRecomendationDAO.getAllEventRecomendations(id);

		if (CollectionUtils.isNotEmpty(eventRecomendations)) {

			List<EventRecomendationResponseDTO> eventRecomendationResponseDTO = new ArrayList<>();
			eventRecomendations.forEach(eventRecomendation -> eventRecomendationResponseDTO
					.add(SetEntitiesIntoDTO.setEventRecomendationResponseDTO(eventRecomendation)));

			return new ResponseEntity<>(
					new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"), messageSource.getMessage("DESC_SUCCESS"),
							messageSource.getMessage("GET_DESC_SUCCESS"), eventRecomendationResponseDTO),
					HttpStatus.OK);

		}

		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_EVENT_PLACE")));

	}

	public ResponseEntity<ResponseDTO> addRecomendationsIntoEvent(
			CreateEventRecomendationDTO createEventRecomendationDTO) throws ValidationException {

		if (createEventRecomendationDTO != null) {

			List<EventRecomendation> recomendations = SetDTOIntoEntities
					.setEventRecomendation(createEventRecomendationDTO);

			boolean upload = eventRecomendationDAO.addRecomendationsIntoEvent(recomendations);
			if (upload) {
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("POST_DESC_SUCCESS"),
						upload), HttpStatus.PRECONDITION_REQUIRED);
			}
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("DESC_ERR"), messageSource.getMessage("POST_DESC_ERROR"), upload),
					HttpStatus.PRECONDITION_REQUIRED);
		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_BODY_PARAMS")));

	}
	
	public ResponseEntity<ResponseDTO> updateEventRecomendation(
			CreateEventRecomendationDTO createEventRecomendationDTO) throws ValidationException {

		if (createEventRecomendationDTO != null) {

			List<EventRecomendation> recomendations = SetDTOIntoEntities
					.setEventRecomendation(createEventRecomendationDTO);

			boolean upload = eventRecomendationDAO.updateEventRecomendation(recomendations);
			if (upload) {
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("DELETE_RECOMENDATION_EVENT_SUCCES"),
						upload), HttpStatus.PRECONDITION_REQUIRED);
			}
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("DESC_ERR"), messageSource.getMessage("DELETE_RECOMENDATION_EVENT_FAILED"), upload),
					HttpStatus.PRECONDITION_REQUIRED);
		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_BODY_PARAMS")));

	}

}
