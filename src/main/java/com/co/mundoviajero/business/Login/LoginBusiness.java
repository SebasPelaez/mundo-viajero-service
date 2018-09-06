package com.co.mundoviajero.business.Login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.util.exception.ValidationException;

public class LoginBusiness {

	@Autowired
	private IPersonDAO personDAO;
	
	@Autowired
	private MessageSourceAccessor messageSource;
	
	public ResponseEntity<ResponseDTO> getPersonWithParameters(Map<String,String> parameters) throws ValidationException {

		PersonDTO personDTO = personDAO.getPersonWithParameters(parameters);

		if (personDTO != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), personDTO),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);

	}
	
	private void setNullAttributes(PersonDTO person) {
		if (person.getIdentification() == null)
			person.setIdentification("");
		if (person.getRnt() == null)
			person.setRnt("");
		if (person.getAddress() == null)
			person.setAddress("");
		if (person.getPassword() == null)
			person.setPassword("");
		if (person.getCalification() == null)
			person.setCalification(0.0);
		if (person.getToken() == null)
			person.setToken("");
		if (person.getProfilePhoto() == null)
			person.setProfilePhoto("");
	}
	
	
}
