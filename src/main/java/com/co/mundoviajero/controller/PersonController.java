package com.co.mundoviajero.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.persistence.entity.Person;

public interface PersonController {
	
	@RequestMapping(value = "/person", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<List<Person>> getPeople();
	
	@RequestMapping(value = "/person", method = RequestMethod.POST, 
			produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
	ResponseEntity<Person> createPerson(@RequestBody Person person);

}
