package com.co.mundoviajero.controller.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
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
import com.co.mundoviajero.util.Constants;
import com.co.mundoviajero.util.exception.ValidationException;

@RestController
public class EventImpl implements EventController{

	@Autowired
	private EventBusiness eventBusiness;
	
	@Autowired
	private EventPlaceBusiness eventPlaceBusiness;

	@Autowired
	private MessageSourceAccessor messageSource;

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
		if(event != null){
			if(!event.getPlaces().isEmpty()){
				return eventBusiness.createEvent(event);
			}
			throw new ValidationException(messageSource.getMessage("NULL_EVENT_PLACES_FOR_EVENT"));
		}
		throw new ValidationException(messageSource.getMessage("NULL_BODY_PARAMS"));
	}

	@Override
	public ResponseEntity<ResponseDTO> updateEvent(@RequestBody Map<String, String> bodyParameters) throws Exception {
		if(!bodyParameters.isEmpty()){
			return eventBusiness.updateEvent(bodyParameters);
		}
		throw new ValidationException(messageSource.getMessage("MISS_BODY_PARAMS"));
	}

	@Override
	public ResponseEntity<ResponseDTO> findNearestEvents(@RequestParam Map<String, String> parameters) throws Exception {
		List<Long> eventsId = eventPlaceBusiness.findNearestEvents(parameters);
		return eventBusiness.getEventsWithId(eventsId);
	}

}
