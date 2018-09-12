package com.co.mundoviajero.controller;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.dto.ResponseDTO;

public interface LoginController {
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> login(@RequestBody Map<String, String> loginParameters) throws Exception;
}
