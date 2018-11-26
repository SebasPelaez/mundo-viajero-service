package com.co.mundoviajero.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.EventRecomendation.EventRecomendationBusiness;
import com.co.mundoviajero.controller.EventRecomendationController;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.EventRecomendation.CreateEventRecomendationDTO;

@RestController
public class EventRecomendationImpl implements EventRecomendationController {

	@Autowired
	private EventRecomendationBusiness eventRecomendationBusiness;
	
	@Override
	public ResponseEntity<ResponseDTO> getAllEventRecomendations(@PathVariable("id") Long id) throws Exception {
		return eventRecomendationBusiness.getEventRecomendations(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> updateEventRecomendation(
			@Valid @RequestBody CreateEventRecomendationDTO createEventRecomendationDTO) throws Exception {
		return eventRecomendationBusiness.updateEventRecomendation(createEventRecomendationDTO);
	}

	@Override
	public ResponseEntity<ResponseDTO> addRecomendationsIntoEvent(
			@Valid @RequestBody CreateEventRecomendationDTO createEventRecomendationDTO) throws Exception {
		return eventRecomendationBusiness.addRecomendationsIntoEvent(createEventRecomendationDTO);
	}

}
