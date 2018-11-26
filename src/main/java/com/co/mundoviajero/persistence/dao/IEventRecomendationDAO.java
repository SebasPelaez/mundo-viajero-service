package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.persistence.entity.EventRecomendation;

public interface IEventRecomendationDAO {
	
	List<EventRecomendation> getAllEventRecomendations(Long id);
	boolean addRecomendationsIntoEvent(List<EventRecomendation> eventRecomendations);
	boolean updateEventRecomendation(List<EventRecomendation> eventRecomendations);	

}
