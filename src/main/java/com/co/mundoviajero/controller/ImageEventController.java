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

public interface ImageEventController {
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/imageevent/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getImageEvent(@PathVariable("id") String id) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/event/imageevent/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getAllImagesForEvent(@PathVariable("id") String id) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/imageevent", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> updateImages(@RequestBody Map<String, String> bodyParameters) throws Exception;

}
