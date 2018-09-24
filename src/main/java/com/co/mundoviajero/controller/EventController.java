package com.co.mundoviajero.controller;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.dto.EventDTO;
import com.co.mundoviajero.dto.ResponseDTO;

public interface EventController {
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/event", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getAllEvents() throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getEvent(@PathVariable("id") String id) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/event/search", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getEventWithParameters(@RequestBody Map<String, Object> parameters) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/event", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> createEvent(@RequestBody EventDTO event) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/event", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> updateEvent(@RequestBody Map<String, String> bodyParameters) throws Exception;

}