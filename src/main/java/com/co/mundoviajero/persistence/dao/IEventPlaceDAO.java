package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.dto.EventPlaceDTO;

public interface IEventPlaceDAO {
	
	EventPlaceDTO getEventPlace(Long id);
	List<EventPlaceDTO> getAllEventPlaces(Long idEvent);
	boolean createEventPlaces(List<EventPlaceDTO> eventPlacesDTO, Long eventId);
	boolean updateEventPlace(Map<String, String> parameters, Long identifier);

}
