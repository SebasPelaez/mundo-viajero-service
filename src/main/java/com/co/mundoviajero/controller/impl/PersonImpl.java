package com.co.mundoviajero.controller.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.Person.PersonBusiness;
import com.co.mundoviajero.controller.PersonController;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.person.CreatePersonDTO;
import com.co.mundoviajero.util.exception.ValidationException;

@RestController
public class PersonImpl implements PersonController{

	@Autowired
	private MessageSourceAccessor messageSource;

	@Autowired
	private PersonBusiness personBusiness;
	
	@Override
	public ResponseEntity<ResponseDTO> getAllPeople()  throws Exception{
		return personBusiness.getAllPeople();
	}

	@Override
	public ResponseEntity<ResponseDTO> createPerson(@RequestBody CreatePersonDTO person)  throws Exception{		
        return personBusiness.createPerson(person);        
	}

	@Override
	public ResponseEntity<ResponseDTO> getPerson(@PathVariable("id") String id) throws Exception {
		
		if(StringUtils.isNotBlank(id)) {
			return personBusiness.getPerson(Long.parseLong(id));
		}
		throw new ValidationException(messageSource.getMessage("MISS_QUERY_PARAMS"));
	}

	@Override
	public ResponseEntity<ResponseDTO> getPersonWithParameters(@RequestParam Map<String, String> parameters) throws Exception {
		if(!parameters.isEmpty()) {
			return personBusiness.getPersonWithParameters(parameters);
		}
		throw new ValidationException(messageSource.getMessage("MISS_QUERY_PARAMS"));
	}

	@Override
	public ResponseEntity<ResponseDTO> updatePerson(@RequestBody Map<String, String> bodyParameters) throws Exception {
		if(!bodyParameters.isEmpty()){
			return personBusiness.updatePerson(bodyParameters);
		}
		throw new ValidationException(messageSource.getMessage("MISS_BODY_PARAMS"));
	}

}
