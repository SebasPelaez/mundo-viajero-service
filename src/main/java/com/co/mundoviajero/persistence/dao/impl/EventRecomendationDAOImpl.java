package com.co.mundoviajero.persistence.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.persistence.dao.IEventRecomendationDAO;
import com.co.mundoviajero.persistence.entity.EventRecomendation;

@Repository(value = "EventRecomendationDAOImpl")
@Transactional
public class EventRecomendationDAOImpl extends BaseDAO implements IEventRecomendationDAO {

	@Override
	public List<EventRecomendation> getAllEventRecomendations(Long id) {
		
		TypedQuery<EventRecomendation> query = getCurrentSession()
	            .createQuery("select er from EventRecomendation er where er.primary.eventId = :id", EventRecomendation.class);
	        
	        query.setParameter("id", id);

	        return (List<EventRecomendation>) query.getResultList();
	}

	@Override
	public boolean addRecomendationsIntoEvent(List<EventRecomendation> eventRecomendations) {

		try {
			for(EventRecomendation eventRecomendation: eventRecomendations) {
				getCurrentSession().saveOrUpdate(eventRecomendation);
			}			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateEventRecomendation(List<EventRecomendation> eventRecomendations) {
		
		boolean succes = true;
		try {
			for(EventRecomendation eventRecomendation: eventRecomendations) {
				getCurrentSession().delete(eventRecomendation);
			}			
		}catch (Exception e) {
			succes = false;
		}
		return succes;
	}

}
