package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.dto.EventDTO;
import com.co.mundoviajero.util.exception.ValidationException;

public interface IEventDAO {

	List<EventDTO> getAllEvents();
	EventDTO getEvent(Long searchParameter);
	List<EventDTO> getEventWithParameters(Map<String, Object> parameters);
	boolean createEvent(EventDTO event) throws ValidationException;
	boolean updateEvent(Map<String, String> parameters, Long identifier);	
	
}
