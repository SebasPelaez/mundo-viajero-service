package com.co.mundoviajero.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.StateDTO;

public interface StateController {
	
	@RequestMapping(value = "/state", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> getAllStates();
	
	@RequestMapping(value = "/state", method = RequestMethod.POST, 
			produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> createState(@RequestBody StateDTO state);
	
	@RequestMapping(value = "/state/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> getState(@PathVariable("id") Long id);
	
}
