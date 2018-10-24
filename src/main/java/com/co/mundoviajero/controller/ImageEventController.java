package com.co.mundoviajero.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import com.co.mundoviajero.dto.event.imageevent.CreateImageEventDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.dto.ResponseDTO;

@RequestMapping(value = "/imageevent")
public interface ImageEventController {
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getImageEvent(@PathVariable("id") Long id) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getAllImagesForEvent(@PathVariable("id") Long id) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON }, consumes = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> updateImages(@RequestBody Map<String, String> bodyParameters) throws Exception;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> uploadImage(@Valid @RequestBody CreateImageEventDTO imageEventDTO) throws Exception;

}
