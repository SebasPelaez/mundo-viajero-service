package com.co.mundoviajero.business.Recomendation;

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
import com.co.mundoviajero.dto.Recomendation.CreateRecomendationDTO;
import com.co.mundoviajero.dto.Recomendation.RecomendationResponseDTO;
import com.co.mundoviajero.persistence.dao.IRecomendationDAO;
import com.co.mundoviajero.persistence.entity.Recomendation;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;
import com.co.mundoviajero.util.exception.dto.ErrorDTO;

@Service
public class RecomendationBusiness {

	@Autowired
	private MessageSourceAccessor messageSource;

	@Autowired
	private IRecomendationDAO recomendationDAO;

	public ResponseEntity<ResponseDTO> getAllRecomendations() throws Exception {

		List<Recomendation> recomendations = recomendationDAO.getAllRecomendations();
		if (CollectionUtils.isNotEmpty(recomendations)) {

			List<RecomendationResponseDTO> recomendationDTO = new ArrayList<>();
			recomendations.forEach(recomendation -> recomendationDTO
					.add(SetEntitiesIntoDTO.setRecomendationResponseDTO(recomendation)));

			return new ResponseEntity<>(
					new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"), messageSource.getMessage("DESC_SUCCESS"),
							messageSource.getMessage("GET_DESC_SUCCESS"), recomendationDTO),
					HttpStatus.OK);

		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR_RECOMENDATION"), null),
				HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseDTO> getRecomendation(Long id) throws ValidationException {

		StringBuilder sb = new StringBuilder(
				Validator.validateLong(id, FieldConstants.RECOMENDATION_ID, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"), sb.toString()));
		}

		Recomendation recomendation = recomendationDAO.getRecomendation(id);

		if (recomendation != null) {

			RecomendationResponseDTO recomendationResponseDTO = SetEntitiesIntoDTO
					.setRecomendationResponseDTO(recomendation);

			return new ResponseEntity<>(
					new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"), messageSource.getMessage("DESC_SUCCESS"),
							messageSource.getMessage("GET_DESC_SUCCESS"), recomendationResponseDTO),
					HttpStatus.OK);
		}
		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_RECOMENDATION")));

	}

	public ResponseEntity<ResponseDTO> createRecomendation(CreateRecomendationDTO createRecomendationDTO)
			throws ValidationException {

		if (createRecomendationDTO != null) {

			Recomendation recomendation = SetDTOIntoEntities.setRecomendation(createRecomendationDTO);
			boolean success = recomendationDAO.createRecomendation(recomendation);

			if (success) {

				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("POST_DESC_SUCCESS"),
						success), HttpStatus.OK);
			}

			throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("FAIL_CREATED_RECOMENDATION")));

		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_BODY_PARAMS")));
	}

}
