package com.co.mundoviajero.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.person.CreatePersonDTO;

@RequestMapping(value = "/person")
public interface PersonController {

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getAllPeople() throws Exception;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> createPerson(@Valid @RequestBody CreatePersonDTO person) throws Exception;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getPerson(@PathVariable("id") Long id) throws Exception;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getPersonWithParameters(@RequestParam Map<String, String> parameters) throws Exception;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> updatePerson(@RequestBody Map<String, String> bodyParameters) throws Exception;

}
