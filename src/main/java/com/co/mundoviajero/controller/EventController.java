package com.co.mundoviajero.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.event.CreateEventDTO;

@RequestMapping(value = "/event")
public interface EventController {
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getAllEvents() throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getEvent(@PathVariable("id") String id) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/nearest", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> findNearestEvents(@RequestParam Map<String, String> parameters) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/guide/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> findGuideEvents(@PathVariable("id") Long id) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getEventWithParameters(@RequestBody Map<String, Object> parameters) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> createEvent(@Valid @RequestBody CreateEventDTO event) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> updateEvent(@RequestBody Map<String, String> bodyParameters) throws Exception;

}
