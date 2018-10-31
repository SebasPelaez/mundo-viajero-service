package com.co.mundoviajero.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.dto.ResponseDTO;

@RequestMapping(value = "/state")
public interface StateController {

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> getAllStates()  throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> getState(@PathVariable("id") Long id)  throws Exception;
}
