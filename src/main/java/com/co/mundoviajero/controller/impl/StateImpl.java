package com.co.mundoviajero.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.State.StateBusiness;
import com.co.mundoviajero.controller.StateController;
import com.co.mundoviajero.dto.ResponseDTO;

@RestController
public class StateImpl implements StateController{

	@Autowired
	private StateBusiness stateBusiness;
	
	@Override
	public ResponseEntity<ResponseDTO> getAllStates() throws Exception {
		return stateBusiness.getAllStates();
	}

	@Override
	public ResponseEntity<ResponseDTO> getState(@PathVariable("id") Long id) throws Exception {
		return stateBusiness.getState(id);
	}

}
