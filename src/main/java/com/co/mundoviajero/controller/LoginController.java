package com.co.mundoviajero.controller;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.co.mundoviajero.dto.LoginDTO;
import com.co.mundoviajero.dto.ResponseDTO;

public interface LoginController {
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/login/login", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	Response login(@RequestBody LoginDTO login) throws Exception;
}
