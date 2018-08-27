package com.co.mundoviajero.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.StateBusiness;
import com.co.mundoviajero.controller.StateController;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.StateDTO;

@RestController
public class StateImpl implements StateController{
	
	@Autowired
    private StateBusiness stateBusiness;

	@Override
	public ResponseEntity<ResponseDTO> getAllStates() {
		return stateBusiness.getAllStates();
	}

	@Override
	public ResponseEntity<ResponseDTO> createState(@RequestBody StateDTO state) {		
        return stateBusiness.createState(state);        
	}

	@Override
	public ResponseEntity<ResponseDTO> getState(@PathVariable("id") Long id) {
		return stateBusiness.getState(id);
	}	
	
}