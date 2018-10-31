package com.co.mundoviajero.controller.impl;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.EventPlace.EventPlaceBusiness;
import com.co.mundoviajero.controller.EventPlaceController;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.event.eventplace.CreateEventPlaceDTO;
import com.co.mundoviajero.util.exception.ValidationException;

@RestController
public class EventPlaceImpl implements EventPlaceController {

	@Autowired
	private EventPlaceBusiness eventPlaceBusiness;

	@Override
	public ResponseEntity<ResponseDTO> getEventPlace(@PathVariable("id") Long id) throws Exception {
		return eventPlaceBusiness.getEventPlace(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> getAllEventPlacesForEvent(@PathVariable("id") Long id)
			throws ValidationException {
		return eventPlaceBusiness.getAllEventPlacesForEvent(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> updateEventPlace(@RequestBody Map<String, String> bodyParameters)
			throws Exception {
		return eventPlaceBusiness.updateEventPlace(bodyParameters);
	}

	@Override
	public ResponseEntity<ResponseDTO> addPlaceIntoEvent(@Valid @RequestBody CreateEventPlaceDTO createEventPlaceDTO)
			throws Exception {
		return eventPlaceBusiness.addPlaceIntoEvent(createEventPlaceDTO);
	}

}
