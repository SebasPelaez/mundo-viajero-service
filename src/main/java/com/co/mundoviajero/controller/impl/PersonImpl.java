package com.co.mundoviajero.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.PersonBusiness;
import com.co.mundoviajero.controller.PersonController;
import com.co.mundoviajero.persistence.entity.Person;

@RestController
public class PersonImpl implements PersonController{
	
	@Autowired
	private PersonBusiness personBusiness;
	
	@Override
	public ResponseEntity<List<Person>> getPeople() {
		
		List<Person> people = personBusiness.getPeople();		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setExpires(1000);	    
		return new ResponseEntity<List<Person>>(people, responseHeaders, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setExpires(1000L);        
        return new ResponseEntity<>(personBusiness.createPerson(person), responseHeaders, HttpStatus.CREATED);
	}
}
