package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.persistence.entity.Event;
import com.co.mundoviajero.util.exception.ValidationException;

public interface IEventDAO {

	List<Event> getAllEvents();
	List<Event> getEventsWithId(List<Long> eventsId);
	Event getEvent(Long id);
	List<Event> findGuideEvents(Long id);
	List<Event> getEventWithParameters(Map<String, Object> parameters);
	Long createEvent(Event event) throws ValidationException;
	boolean updateEvent(Map<String, String> parameters, Long identifier);
	boolean validResponsible(Long personIdResponsible);
	int numberOfAtendees(Long eventId);
	
}
