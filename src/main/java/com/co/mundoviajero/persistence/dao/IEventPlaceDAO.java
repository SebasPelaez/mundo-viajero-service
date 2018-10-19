package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.dto.event.eventplace.CreateEventPlaceDTO;
import com.co.mundoviajero.dto.event.eventplace.EventPlaceDTO;
import com.co.mundoviajero.util.BoundingBoxDistance.BoundingBox;

public interface IEventPlaceDAO {
	
	EventPlaceDTO getEventPlace(Long id);
	List<EventPlaceDTO> getAllEventPlaces(Long idEvent);
	boolean createEventPlaces(List<CreateEventPlaceDTO> eventPlacesDTO, Long eventId);
	boolean updateEventPlace(Map<String, String> parameters, Long identifier);
	List<Long> findNearestEvents(BoundingBox boundingBox);

}
