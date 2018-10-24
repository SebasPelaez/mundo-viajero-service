package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.persistence.entity.EventPlace;
import com.co.mundoviajero.util.BoundingBoxDistance.BoundingBox;

public interface IEventPlaceDAO {
	
	EventPlace getEventPlace(Long id);
	List<EventPlace> getAllEventPlaces(Long idEvent);
	boolean createEventPlaces(List<EventPlace> eventPlaces);
	boolean updateEventPlace(Map<String, String> parameters, Long identifier);
	List<Long> findNearestEvents(BoundingBox boundingBox);

}
