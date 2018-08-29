package com.co.mundoviajero.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.Person.PersonBusiness;
import com.co.mundoviajero.controller.PersonController;
import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.dto.ResponseDTO;

@RestController
public class PersonImpl implements PersonController{
	
	@Autowired
	private PersonBusiness personBusiness;
	
	@Override
	public ResponseEntity<ResponseDTO> getAllPersons() {
		return personBusiness.getAllPersons();
	}

	@Override
	public ResponseEntity<ResponseDTO> createPerson(@RequestBody PersonDTO person) {		
        return personBusiness.createPerson(person);        
	}
}
