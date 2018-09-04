package com.co.mundoviajero.business.State;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.StateDTO;
import com.co.mundoviajero.persistence.dao.IStateDAO;
import com.co.mundoviajero.util.exception.ValidationException;


@Service
public class StateBusiness {

	@Autowired
	private IStateDAO stateDAO;

	@Autowired
	private MessageSourceAccessor messageSource;
	
	public ResponseEntity<ResponseDTO> getAllStates() throws Exception {
		List<StateDTO> states = stateDAO.getAllStates();
		if (states != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), states),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseDTO> getState(Long searchParameter) throws ValidationException {
		StateDTO state = stateDAO.getState(searchParameter);
		
		if(state != null) {
			
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), state),
					HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);

	}
	
}
