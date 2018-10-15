package com.co.mundoviajero.controller;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.dto.ResponseDTO;

public interface EventPlaceController {

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/eventplace/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getEventPlace(@PathVariable("id") String id) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/event/eventplace/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getAllEventPlacesForEvent(@PathVariable("id") String id) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/eventplace", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> updateEventPlace(@RequestBody Map<String, String> bodyParameters) throws Exception;
}
