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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.dto.event.CreateEventDTO;
import com.co.mundoviajero.dto.event.EventDTO;
import com.co.mundoviajero.persistence.dao.IEventDAO;
import com.co.mundoviajero.persistence.dao.IEventPlaceDAO;
import com.co.mundoviajero.persistence.entity.Event;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.persistence.entity.State;
import com.co.mundoviajero.util.exception.ValidationException;

@Repository(value = "EventDAOImpl")
@Transactional
public class EventDAOImpl extends BaseDAO implements IEventDAO{
	
	@Autowired
	private IEventPlaceDAO eventPlaceDAO;

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
			eDTO.setPlaces(eventPlaceDAO.getAllEventPlaces(eDTO.getId()));
			eventDTO.add(eDTO);
		}
		
		return eventDTO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventDTO> getEventsWithId(List<Long> eventsId) {
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("select e from Event e where e.id IN (");
		for (Long id : eventsId) {
			stringBuffer.append(id+",");
		}
		stringBuffer.replace(stringBuffer.length()-1, stringBuffer.length(), ")");
		
		Query query = getCurrentSession().createQuery(stringBuffer.toString());
		List<Event> events = (List<Event>) query.getResultList();
		
		if (events.isEmpty())
			return null;
		
		List<EventDTO> eventDTO = new ArrayList<>();
		for (Event e : events) {
			EventDTO eDTO = setEventDTO(e);
			eDTO.setPlaces(eventPlaceDAO.getAllEventPlaces(eDTO.getId()));
			eventDTO.add(eDTO);
		}
		
		return eventDTO;
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
		eventDTO.setPlaces(eventPlaceDAO.getAllEventPlaces(eventDTO.getId()));
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
			eDTO.setPlaces(eventPlaceDAO.getAllEventPlaces(eDTO.getId()));
			eventDTO.add(eDTO);
		}
		return eventDTO;
	}
	
	@Override
	public boolean createEvent(CreateEventDTO event) throws ValidationException {

		Event newEvent = setEvent(event);
		
		try {
			getCurrentSession().saveOrUpdate(newEvent);
			Map<String, Object> parameter = new HashMap<>();
			parameter.put("personIdResponsible", event.getPersonIdResponsible());
			List<EventDTO> events = getEventWithParameters(parameter);
			
			Long eventId = events.get(0).getId();
			event.setId(eventId);
			
			if (!eventPlaceDAO.createEventPlaces(event.getPlaces(), eventId)) {
				return false;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;		
	}
	
	@Override
	public boolean updateEvent(Map<String, String> parameters, Long identifier) {
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
	
	@Override
	public boolean validResponsible(Long personIdResponsible) {
		String queryString = "select p from Person p where p.id = :personIdResponsible and p.stateId = 16";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("personIdResponsible", personIdResponsible);
		return !query.getResultList().isEmpty();
	}
	
	private EventDTO setEventDTO(Event event) {
		EventDTO eventDTO = new EventDTO();
		
		try {
			eventDTO.setId(event.getId());
			eventDTO.setName(event.getName().trim());
			eventDTO.setDescription(event.getDescription().trim());
			eventDTO.setStartDate(event.getStartDate().toString().trim());
			eventDTO.setEndDate(event.getEndDate().toString().trim());
			eventDTO.setLongitudeMeetingPoint(String.valueOf(event.getLongitudeMeetingPoint()));
			eventDTO.setLatitudeMeetingPoint(String.valueOf(event.getLatitudeMeetingPoint()));
			eventDTO.setCapaciticy(event.getCapaciticy());
			eventDTO.setFare(event.getFare());
			eventDTO.setPersonIdResponsible(event.getPersonIdResponsible());
			eventDTO.setState(event.getStateId());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return eventDTO;
	}	

	private Event setEvent(CreateEventDTO eventDTO) {
		Event event = new Event();

		try {
			event.setName(eventDTO.getName().trim());
			event.setDescription(eventDTO.getDescription().trim());
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = format.parse(eventDTO.getStartDate());
			Date endDate = format.parse(eventDTO.getEndDate());
			
			java.sql.Timestamp startDateSql = new java.sql.Timestamp(startDate.getTime());
			java.sql.Timestamp endDateSql = new java.sql.Timestamp(endDate.getTime());
			
			event.setStartDate(startDateSql);
			event.setEndDate(endDateSql);
			
			event.setLongitudeMeetingPoint(Double.parseDouble(eventDTO.getLongitudeMeetingPoint().trim()));
			event.setLatitudeMeetingPoint(Double.parseDouble(eventDTO.getLatitudeMeetingPoint().trim()));
			event.setCapaciticy(eventDTO.getCapaciticy());
			event.setFare(eventDTO.getFare());
			
			Person person = new Person();
			person.setId(Long.parseLong(eventDTO.getPersonIdResponsible()));
			event.setPersonIdResponsible(person);
			
			State state = new State();
			state.setId(Long.parseLong(eventDTO.getStateId()));
			event.setStateId(state);
						
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return event;
	}

}
