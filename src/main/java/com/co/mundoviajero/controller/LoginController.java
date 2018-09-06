package com.co.mundoviajero.controller;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.co.mundoviajero.dto.ResponseDTO;

public interface LoginController {
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/person/search", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getPersonWithParameters(@RequestParam Map<String, String> parameters) throws Exception;
}
