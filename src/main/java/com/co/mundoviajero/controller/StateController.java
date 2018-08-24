package com.co.mundoviajero.controller;

import com.co.mundoviajero.persistence.entity.State;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

public interface StateController {
	
	@RequestMapping("/state")
	ResponseEntity<List<State>> getStates();

}
