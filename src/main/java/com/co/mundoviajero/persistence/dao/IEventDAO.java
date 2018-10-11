package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.dto.event.CreateEventDTO;
import com.co.mundoviajero.dto.event.EventDTO;
import com.co.mundoviajero.util.exception.ValidationException;

public interface IEventDAO {

	List<EventDTO> getAllEvents();
	List<EventDTO> getEventsWithId(List<Long> eventsId);
	EventDTO getEvent(Long searchParameter);
	List<EventDTO> getEventWithParameters(Map<String, Object> parameters);
	String createEvent(CreateEventDTO event) throws ValidationException;
	boolean updateEvent(Map<String, String> parameters, Long identifier);
	boolean validResponsible(Long personIdResponsible);
	
}
