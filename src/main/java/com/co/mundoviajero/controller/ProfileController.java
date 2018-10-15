package com.co.mundoviajero.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.dto.ResponseDTO;

public interface ProfileController {

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/profile", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> getAllProfiles()  throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/profile/{search}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<ResponseDTO> getProfile(@PathVariable("search") String search)  throws Exception;
	
}
