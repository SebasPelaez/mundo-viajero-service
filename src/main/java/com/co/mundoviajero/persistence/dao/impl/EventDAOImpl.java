package com.co.mundoviajero.persistence.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.dto.EventDTO;
import com.co.mundoviajero.dto.EventPlaceDTO;
import com.co.mundoviajero.persistence.dao.IEventDAO;
import com.co.mundoviajero.persistence.entity.Event;
import com.co.mundoviajero.persistence.entity.EventPlace;
import com.co.mundoviajero.util.exception.ValidationException;

@Repository(value = "EventDAOImpl")
@Transactional
public class EventDAOImpl extends BaseDAO implements IEventDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<EventDTO> getAllEvents() {
		Query query = getCurrentSession().createQuery("From Event");
		List<Event> events = (List<Event>) query.getResultList();
		
		if (events.isEmpty())
			return null;
		
		List<EventDTO> eventDTO = new ArrayList<>();
		for (Event e : events) {
			EventDTO eDTO = setEventDTO(e);
			eDTO.setPlaces(getAllEventPlaces(eDTO.getId()));
			eventDTO.add(eDTO);
		}
		
		return eventDTO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventPlaceDTO> getAllEventPlaces(Long eventId) {
		
		String queryString = "select ep from EventPlace ep where ep.eventId = :eventId";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("eventId", eventId);
		
		List<EventPlace> places = (List<EventPlace>)query.getResultList();
		List<EventPlaceDTO> placesDTO = new ArrayList<>();

		if (places.isEmpty())
			return null;
		
		for (EventPlace ep : places) {
			placesDTO.add(setEventPlaceDTO(ep));
		}
		return placesDTO;
	}

	@Override
	public EventDTO getEvent(Long id) {
		EventDTO eventDTO = null;
		String queryString = "select e from Event e where e.id = :id";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("id", id);

		if (query.getResultList().isEmpty())
			return null;
		
		eventDTO = setEventDTO((Event) query.getSingleResult());
		eventDTO.setPlaces(getAllEventPlaces(eventDTO.getId()));
		return eventDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventDTO> getEventWithParameters(Map<String, Object> parameters) {
		StringBuffer parametersQueryString = new StringBuffer();
		parametersQueryString.append("select e from Event e where ");
		
		Set<Entry<String, Object>> entries = parameters.entrySet();		
		for (Entry<String, Object> entry : entries){
			parametersQueryString.append("e."+entry.getKey()+" = '"+entry.getValue()+"'AND ");
		}
		parametersQueryString.replace(parametersQueryString.length()-4, parametersQueryString.length(), "");
		parametersQueryString.append(" ORDER BY e.id DESC");
				
		Query query = getCurrentSession().createQuery(parametersQueryString.toString());
		
		List<Event> events = (List<Event>) query.getResultList();
		if (events.isEmpty())
			return null;		
		
		List<EventDTO> eventDTO = new ArrayList<>();
		for (Event e : events) {
			EventDTO eDTO = setEventDTO(e);
			eDTO.setPlaces(getAllEventPlaces(eDTO.getId()));
			eventDTO.add(eDTO);
		}
		return eventDTO;
	}
	
	@Override
	public EventDTO createEvent(EventDTO event) throws ValidationException {

		Event newEvent = setEvent(event);
		
		try {
			getCurrentSession().saveOrUpdate(newEvent);
			Map<String, Object> parameter = new HashMap<>();
			parameter.put("personIdResponsible", event.getPersonIdResponsible());
			List<EventDTO> events = getEventWithParameters(parameter);
			
			Long eventId = events.get(0).getId();
			event.setId(eventId);
			
			List<EventPlaceDTO> eventPlaces = createEventPlaces(event.getPlaces(), eventId);
			if (eventPlaces == null) {
				return null;
			}
			event.setPlaces(eventPlaces);
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return event;		
	}
	
	private List<EventPlaceDTO> createEventPlaces(List<EventPlaceDTO> eventPlacesDTO, Long eventId) throws ValidationException {

		List<EventPlace> eventPlaces = setEventPlace(eventPlacesDTO, eventId);

		try {
			for (EventPlace ep: eventPlaces) {
				getCurrentSession().saveOrUpdate(ep);
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return eventPlacesDTO;
	}
	
	@Override
	public boolean updateEvent(Map<String, String> parameters, Long identifier) throws ValidationException {
		StringBuffer parametersQueryString = new StringBuffer();
		String baseQueryString = "update Event e set ";
		String conditionQueryString = " where e.id = :id";
		
		try {
			
			for(String parameter: parameters.keySet()) {
				parametersQueryString.append("e."+parameter+" = '"+parameters.get(parameter)+"', ");
			}
			
			parametersQueryString.replace(parametersQueryString.length()-2, parametersQueryString.length(), "");
			String fullQueryString = baseQueryString + parametersQueryString.toString() + conditionQueryString;
			
			Query query = getCurrentSession().createQuery(fullQueryString);
			query.setParameter("id",identifier);
			query.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	private EventDTO setEventDTO(Event event) {
		EventDTO eventDTO = new EventDTO();
		
		try {
			eventDTO.setId(event.getId());
			eventDTO.setName(event.getName().trim());
			eventDTO.setDescription(event.getDescription().trim());
			eventDTO.setStartDate(event.getStartDate().toString().trim());
			eventDTO.setEndDate(event.getEndDate().toString().trim());
			eventDTO.setAltitudeMeetingPoint(event.getAltitudeMeetingPoint().trim());
			eventDTO.setLatitudeMeetingPoint(event.getLatitudeMeetingPoint().trim());
			eventDTO.setCapaciticy(event.getCapaciticy());
			eventDTO.setFare(event.getFare());
			eventDTO.setPersonIdResponsible(event.getPersonIdResponsible());
			eventDTO.setStateId(event.getStateId());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return eventDTO;
	}	
	
	private EventPlaceDTO setEventPlaceDTO(EventPlace eventPlace) {
		EventPlaceDTO eventPlaceDTO = new EventPlaceDTO();
		
		try {
			eventPlaceDTO.setId(eventPlace.getId());
			eventPlaceDTO.setEventId(eventPlace.getEventId());
			eventPlaceDTO.setCityId(eventPlace.getCityId());
			eventPlaceDTO.setEventPlaceStartDate(eventPlace.getEventPlaceStartDate().toString().trim());
			eventPlaceDTO.setEventPlaceEndDate(eventPlace.getEventPlaceEndDate().toString().trim());
			eventPlaceDTO.setAltitudeEventPlace(eventPlace.getAltitudeEventPlace().trim());
			eventPlaceDTO.setLatitudeEventPlace(eventPlace.getLatitudeEventPlace().trim());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return eventPlaceDTO;
	}

	private Event setEvent(EventDTO eventDTO) {
		Event event = new Event();

		try {
			event.setId(eventDTO.getId());
			event.setName(eventDTO.getName().trim());
			event.setDescription(eventDTO.getDescription().trim());
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = format.parse(eventDTO.getStartDate());
			Date endDate = format.parse(eventDTO.getEndDate());
			
			java.sql.Date startDateSql = new java.sql.Date(startDate.getTime());
			java.sql.Date endDateSql = new java.sql.Date(endDate.getTime());
			
			event.setStartDate(startDateSql);
			event.setEndDate(endDateSql);
			
			event.setAltitudeMeetingPoint(eventDTO.getAltitudeMeetingPoint().trim());
			event.setLatitudeMeetingPoint(eventDTO.getLatitudeMeetingPoint().trim());
			event.setCapaciticy(eventDTO.getCapaciticy());
			event.setFare(eventDTO.getFare());
			event.setPersonIdResponsible(eventDTO.getPersonIdResponsible());
			event.setStateId(eventDTO.getStateId());
						
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return event;
	}
	
	private List<EventPlace> setEventPlace(List<EventPlaceDTO> eventPlacesDTO, Long eventId) {
		
		List<EventPlace> eventPlaces = new ArrayList<>();

		try {
			for(EventPlaceDTO evtDTO: eventPlacesDTO) {
				
				EventPlace eventPlace = new EventPlace();
				eventPlace.setEventId(eventId);
				evtDTO.setEventId(eventId);
				
				eventPlace.setCityId(evtDTO.getCityId());
				eventPlace.setAltitudeEventPlace(evtDTO.getAltitudeEventPlace());
				eventPlace.setLatitudeEventPlace(evtDTO.getLatitudeEventPlace());
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date startDate = format.parse(evtDTO.getEventPlaceStartDate());
				Date endDate = format.parse(evtDTO.getEventPlaceEndDate());
				
				java.sql.Date startDateSql = new java.sql.Date(startDate.getTime());
				java.sql.Date endDateSql = new java.sql.Date(endDate.getTime());
				
				eventPlace.setEventPlaceStartDate(startDateSql);
				eventPlace.setEventPlaceEndDate(endDateSql);
				
				eventPlaces.add(eventPlace);
			}
						
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return eventPlaces;
	}

}
