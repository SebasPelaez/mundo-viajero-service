package com.co.mundoviajero.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.Person.PersonBusiness;
import com.co.mundoviajero.controller.PersonController;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.person.CreatePersonDTO;

@RestController
public class PersonImpl implements PersonController{

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
	public ResponseEntity<ResponseDTO> getPerson(@PathVariable("id") Long id) throws Exception {
		return personBusiness.getPerson(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> getPersonWithParameters(@RequestParam Map<String, String> parameters) throws Exception {
		return personBusiness.getPersonWithParameters(parameters);
	}

	@Override
	public ResponseEntity<ResponseDTO> updatePerson(@RequestBody Map<String, String> bodyParameters) throws Exception {
		return personBusiness.updatePerson(bodyParameters);
	}

}
