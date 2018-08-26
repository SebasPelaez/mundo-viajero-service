package com.co.mundoviajero.controller;


import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.persistence.entity.State;

public interface StateController {
	
	@RequestMapping(value = "/state", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
	ResponseEntity<List<State>> getStates();
	
	@RequestMapping(value = "/state", method = RequestMethod.POST, 
			produces = {MediaType.APPLICATION_JSON}, consumes = {MediaType.APPLICATION_JSON})
	ResponseEntity<State> createState(@RequestBody State state);

}
