package com.co.mundoviajero.controller.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.Event.EventBusiness;
import com.co.mundoviajero.business.EventPlace.EventPlaceBusiness;
import com.co.mundoviajero.controller.EventController;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.event.CreateEventDTO;

@RestController
public class EventImpl implements EventController{

	@Autowired
	private EventBusiness eventBusiness;
	
	@Autowired
	private EventPlaceBusiness eventPlaceBusiness;

	@Override
	public ResponseEntity<ResponseDTO> getAllEvents() throws Exception {
		return eventBusiness.getAllEvents();
	}

	@Override
	public ResponseEntity<ResponseDTO> getEvent(@PathVariable("id") String id) throws Exception {
		return eventBusiness.getEvent(Long.parseLong(id));
	}

	@Override
	public ResponseEntity<ResponseDTO> getEventWithParameters(@RequestBody Map<String, Object> parameters) throws Exception {
		return eventBusiness.getEventWithParameters(parameters);
	}

	@Override
	public ResponseEntity<ResponseDTO> createEvent(@RequestBody CreateEventDTO event) throws Exception {
		return eventBusiness.createEvent(event);
	}

	@Override
	public ResponseEntity<ResponseDTO> updateEvent(@RequestBody Map<String, String> bodyParameters) throws Exception {
		return eventBusiness.updateEvent(bodyParameters);
	}

	@Override
	public ResponseEntity<ResponseDTO> findNearestEvents(@RequestParam Map<String, String> parameters) throws Exception {
		List<Long> eventsId = eventPlaceBusiness.findNearestEvents(parameters);
		return eventBusiness.getEventsWithId(eventsId);
	}

	@Override
	public ResponseEntity<ResponseDTO> findGuideEvents(@PathVariable("id") Long id) throws Exception {
		return eventBusiness.findGuideEvents(id);
	}

}
