package com.co.mundoviajero.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<ResponseDTO> getAllPeople()  throws Exception{
		return personBusiness.getAllPeople();
	}

	@Override
	public ResponseEntity<ResponseDTO> createPerson(@RequestBody PersonDTO person)  throws Exception{		
        return personBusiness.createPerson(person);        
	}

	@Override
	public ResponseEntity<ResponseDTO> getPerson(@PathVariable("search") String search) throws Exception {
		return personBusiness.getPerson(search);
	}

	@Override
	public ResponseEntity<ResponseDTO> updatePerson(@RequestBody PersonDTO person, @PathVariable String search) throws Exception {		
		return personBusiness.updatePerson(person, search);
	}

	@Override
	public ResponseEntity<ResponseDTO> deletePerson(@PathVariable("search") String search) throws Exception {
		return personBusiness.deletePerson(search);
	}
}
