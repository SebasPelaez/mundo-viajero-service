package com.co.mundoviajero.controller;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.dto.ResponseDTO;

public interface PersonController {

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/person", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getAllPeople() throws Exception;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/person", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> createPerson(@RequestBody PersonDTO person) throws Exception;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getPerson(@PathVariable("id") String id) throws Exception;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/person/search", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getPersonWithParameters(@RequestParam Map<String, String> parameters) throws Exception;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/person", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> updatePerson(@RequestBody Map<String, String> bodyParameters) throws Exception;

}
