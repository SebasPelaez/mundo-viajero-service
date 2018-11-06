package com.co.mundoviajero.persistence.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.co.mundoviajero.persistence.dao.IEventDAO;
import com.co.mundoviajero.persistence.entity.Event;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.util.exception.ValidationException;

@Repository(value = "EventDAOImpl")
@Transactional
public class EventDAOImpl extends BaseDAO implements IEventDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllEvents() {
		List<Event> events = new ArrayList<>();
		Query query = getCurrentSession().createQuery("From Event");
		events = (List<Event>) query.getResultList();		
		return events;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventsWithId(List<Long> eventsId) {
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("select e from Event e where e.id IN (");
		for (Long id : eventsId) {
			stringBuffer.append(id+",");
		}
		stringBuffer.replace(stringBuffer.length()-1, stringBuffer.length(), ")");
		
		Query query = getCurrentSession().createQuery(stringBuffer.toString());
		List<Event> events = (List<Event>) query.getResultList();
				
		return events;
	}

	@Override
	public Event getEvent(Long id) {		
		return getCurrentSession().find(Event.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findGuideEvents(Long guideId) {
		
		Person personIdResponsible = new Person();
		personIdResponsible.setId(guideId);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("select e from Event e where e.personIdResponsible = :personIdResponsible");
		
		Query query = getCurrentSession().createQuery(stringBuffer.toString());
		query.setParameter("personIdResponsible",personIdResponsible);
		
		List<Event> events = (List<Event>) query.getResultList();
				
		return events;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventWithParameters(Map<String, Object> parameters) {
		
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
		return events;
	}
	
	@Override
	public Long createEvent(Event event) throws ValidationException {
	
		Long eventId = -1L;
		try {
			getCurrentSession().saveOrUpdate(event);
			Map<String, Object> parameter = new HashMap<>();
			parameter.put("personIdResponsible", event.getPersonIdResponsible().getId());
			List<Event> events = getEventWithParameters(parameter);
			eventId = events.get(0).getId();
		} catch (Exception e) {
			System.out.println(e);
		}
		return eventId;		
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
		String queryString = "select p from Person p where p.id = :personIdResponsible and p.state = 16 and p.profile = 2";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("personIdResponsible", personIdResponsible);
		return !query.getResultList().isEmpty();
	}

}
