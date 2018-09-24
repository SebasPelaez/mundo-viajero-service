package com.co.mundoviajero.controller.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.EventPlace.EventPlaceBusiness;
import com.co.mundoviajero.controller.EventPlaceController;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.util.exception.ValidationException;

@RestController
public class EventPlaceImpl implements EventPlaceController {

	@Autowired
	private EventPlaceBusiness eventPlaceBusiness;
	
	@Override
	public ResponseEntity<ResponseDTO> getEventPlace(@PathVariable("id") String id) throws Exception {
		if(StringUtils.isNotBlank(id)) {
			return eventPlaceBusiness.getEventPlace(Long.parseLong(id));
		}
		throw new ValidationException("No hay parámetro de búsqueda");
	}

	@Override
	public ResponseEntity<ResponseDTO> getAllEventPlacesForEvent(@PathVariable("id") String id) throws ValidationException{
		if(StringUtils.isNotBlank(id)) {
			return eventPlaceBusiness.getAllEventPlacesForEvent(Long.parseLong(id));
		}
		throw new ValidationException("No hay parámetro de búsqueda");
	}

	@Override
	public ResponseEntity<ResponseDTO> updateEventPlace(@RequestBody Map<String, String> bodyParameters) throws Exception {
		if(!bodyParameters.isEmpty()){
			return eventPlaceBusiness.updateEventPlace(bodyParameters);
		}
		throw new ValidationException("El body esta vacio");
	}

}
