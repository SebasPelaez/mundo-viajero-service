package com.co.mundoviajero.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.co.mundoviajero.persistence.entity.State;

public interface StateController {
	
	@RequestMapping("/state")
	ResponseEntity<List<State>> getStates();
	
	@RequestMapping("/example")
	String prueba();

}
