package com.co.mundoviajero.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.dto.ResponseDTO;

public interface PersonController {
	
	@RequestMapping(value = "/person", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> getAllPersons();
	
	@RequestMapping(value = "/person", method = RequestMethod.POST, 
			produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> createPerson(@RequestBody PersonDTO person);
	
}
