package com.co.mundoviajero.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.StateBusiness;
import com.co.mundoviajero.controller.StateController;
import com.co.mundoviajero.persistence.entity.State;

@RestController
public class StateImpl implements StateController{
	
	@Autowired
    private StateBusiness stateBusiness;

	@Override
	public ResponseEntity<List<State>> getStates() {
		
		List<State> states = stateBusiness.getStates();
		
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setExpires(1000);
	    
		return new ResponseEntity<List<State>>(states, responseHeaders, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<State> createState(@RequestBody State state) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setExpires(1000L);
        
        return new ResponseEntity<>(stateBusiness.createState(state), responseHeaders, HttpStatus.CREATED);
        
	}
	
}