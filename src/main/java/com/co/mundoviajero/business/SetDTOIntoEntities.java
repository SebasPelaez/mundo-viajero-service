package com.co.mundoviajero.business;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.co.mundoviajero.dto.event.CreateEventDTO;
import com.co.mundoviajero.dto.event.eventplace.CreateEventPlaceDTO;
import com.co.mundoviajero.dto.event.imageevent.CreateImageEventDTO;
import com.co.mundoviajero.persistence.entity.City;
import com.co.mundoviajero.persistence.entity.Event;
import com.co.mundoviajero.persistence.entity.EventPlace;
import com.co.mundoviajero.persistence.entity.ImageEvent;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.persistence.entity.State;

public class SetDTOIntoEntities {
	
	public static Event setEvent(CreateEventDTO eventDTO) {
		
		Event event = new Event();

		try {
			event.setName(eventDTO.getName());
			event.setDescription(eventDTO.getDescription());
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = format.parse(eventDTO.getStartDate());
			Date endDate = format.parse(eventDTO.getEndDate());
			
			java.sql.Timestamp startDateSql = new java.sql.Timestamp(startDate.getTime());
			java.sql.Timestamp endDateSql = new java.sql.Timestamp(endDate.getTime());
			
			event.setStartDate(startDateSql);
			event.setEndDate(endDateSql);
			
			event.setLongitudeMeetingPoint(Double.parseDouble(eventDTO.getLongitudeMeetingPoint()));
			event.setLatitudeMeetingPoint(Double.parseDouble(eventDTO.getLatitudeMeetingPoint()));
			event.setCapaciticy(eventDTO.getCapaciticy());
			event.setFare(eventDTO.getFare());
			
			Person person = new Person();
			person.setId(Long.parseLong(eventDTO.getPersonIdResponsible()));
			event.setPersonIdResponsible(person);
			
			State state = new State();
			state.setId(Long.parseLong(eventDTO.getStateId()));
			event.setState(state);
						
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return event;
	}
	
	public static List<EventPlace> setEventPlace(List<CreateEventPlaceDTO> eventPlacesDTO, Long eventId) {

		List<EventPlace> eventPlaces = new ArrayList<>();

		try {
			for (CreateEventPlaceDTO evtDTO : eventPlacesDTO) {

				EventPlace eventPlace = new EventPlace();
				eventPlace.setEventId(eventId);

				City city = new City();
				city.setId(Long.parseLong(evtDTO.getCityId()));
				eventPlace.setCityId(city);
				
				eventPlace.setLongitudeEventPlace(Double.parseDouble(evtDTO.getLongitudeEventPlace()));
				eventPlace.setLatitudeEventPlace(Double.parseDouble(evtDTO.getLatitudeEventPlace()));

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startDate = format.parse(evtDTO.getEventPlaceStartDate());
				Date endDate = format.parse(evtDTO.getEventPlaceEndDate());

				java.sql.Timestamp startDateSql = new java.sql.Timestamp(startDate.getTime());
				java.sql.Timestamp endDateSql = new java.sql.Timestamp(endDate.getTime());

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
	
	public static List<ImageEvent> setImageEvent(CreateImageEventDTO createImageEventDTO) {

		List<ImageEvent> imageEvents  = new ArrayList<>();
		try {
			for (String image : createImageEventDTO.getImages()) {

				ImageEvent imageEvent = new ImageEvent();

				imageEvent.setEventId(createImageEventDTO.getEventId());
				imageEvent.setImagePath(image);

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startDate = format.parse(LocalDateTime.now().toString().replace("T", " "));
				java.sql.Timestamp uploadDateSql = new java.sql.Timestamp(startDate.getTime());
				imageEvent.setUploadDate(uploadDateSql);
				
				State state = new State();
				state.setId(Long.parseLong(createImageEventDTO.getStateId()));
				imageEvent.setState(state);

				imageEvents.add(imageEvent);
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return imageEvents;
	}

}
