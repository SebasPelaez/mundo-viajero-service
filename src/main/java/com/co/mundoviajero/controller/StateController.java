package com.co.mundoviajero.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.dto.ResponseDTO;

public interface StateController {

	@RequestMapping(value = "/state", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> getAllStates()  throws Exception;
	
	@RequestMapping(value = "/state/{search}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> getState(@PathVariable("search") String search)  throws Exception;
	
}
