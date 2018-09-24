package com.co.mundoviajero.controller.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.Event.EventBusiness;
import com.co.mundoviajero.controller.EventController;
import com.co.mundoviajero.dto.EventDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.util.exception.ValidationException;

@RestController
public class EventImpl implements EventController{

	@Autowired
	private EventBusiness eventBusiness;
	
	@Override
	public ResponseEntity<ResponseDTO> getAllEvents() throws Exception {
		return eventBusiness.getAllEvents();
	}

	@Override
	public ResponseEntity<ResponseDTO> getEvent(@PathVariable("id") String id) throws Exception {
		if(StringUtils.isNotBlank(id)) {
			return eventBusiness.getEvent(Long.parseLong(id));
		}
		throw new ValidationException("No hay parámetro de búsqueda");
	}

	@Override
	public ResponseEntity<ResponseDTO> getEventWithParameters(@RequestBody Map<String, Object> parameters) throws Exception {
		if(!parameters.isEmpty()) {
			return eventBusiness.getEventWithParameters(parameters);
		}
		throw new ValidationException("El body esta vacío");
	}

	@Override
	public ResponseEntity<ResponseDTO> createEvent(@RequestBody EventDTO event) throws Exception {
		return eventBusiness.createEvent(event);
	}

	@Override
	public ResponseEntity<ResponseDTO> updateEvent(@RequestBody Map<String, String> bodyParameters) throws Exception {
		if(!bodyParameters.isEmpty()){
			return eventBusiness.updateEvent(bodyParameters);
		}
		throw new ValidationException("El body esta vacio");
	}

}
