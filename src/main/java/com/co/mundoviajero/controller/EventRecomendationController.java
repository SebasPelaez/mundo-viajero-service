package com.co.mundoviajero.controller;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.EventRecomendation.CreateEventRecomendationDTO;

@RequestMapping(value = "/eventrecomendation")
public interface EventRecomendationController {

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> getAllEventRecomendations(@PathVariable("id") Long id) throws Exception;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON }, consumes = {
			MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> updateEventRecomendation(
			@Valid @RequestBody CreateEventRecomendationDTO createEventRecomendationDTO) throws Exception;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON })
	ResponseEntity<ResponseDTO> addRecomendationsIntoEvent(
			@Valid @RequestBody CreateEventRecomendationDTO createEventRecomendationDTO) throws Exception;

}
