package com.co.mundoviajero.business.State;

import java.util.ArrayList;
import java.util.List;

import com.co.mundoviajero.persistence.entity.State;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.dto.ErrorDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.state.StateResponseDTO;
import com.co.mundoviajero.persistence.dao.IStateDAO;
import com.co.mundoviajero.util.exception.ValidationException;


@Service
public class StateBusiness {

	@Autowired
	private IStateDAO stateDAO;

	@Autowired
	private MessageSourceAccessor messageSource;
	
	public ResponseEntity<ResponseDTO> getAllStates() throws Exception {
		List<State> states = stateDAO.getAllStates();
		if(CollectionUtils.isNotEmpty(states)){

			List<StateResponseDTO> stateResponse = new ArrayList<>();
			states.forEach(
					state -> stateResponse
							.add(new StateResponseDTO(state.getId(),state.getDescription(),state.getBelongsTo()))
			);
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					stateResponse),HttpStatus.OK);
		}
		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_STATE")));
	}

	public ResponseEntity<ResponseDTO> getState(Long id) throws ValidationException {

		StringBuilder sb = new StringBuilder(Validator.validateLong(
				id, FieldConstants.STATE_ID, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"),
					sb.toString()));
		}

		State state = stateDAO.getState(id);

		if(state != null) {

			StateResponseDTO stateResponseDTO =
					new StateResponseDTO(state.getId(),state.getDescription(),state.getBelongsTo());
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					stateResponseDTO),HttpStatus.OK);
		}
		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_STATE")));
	}
	
}
