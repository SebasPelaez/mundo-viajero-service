package com.co.mundoviajero.dto.event;

import java.util.List;

import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.persistence.entity.State;

public class EventDTO extends BaseEventDTO{

	private static final long serialVersionUID = 1L;
	
	private Person personIdResponsible;
	private State state;
	private List<EventPlaceDTO> places;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Person getPersonIdResponsible() {
		return personIdResponsible;
	}
	public void setPersonIdResponsible(Person personIdResponsible) {
		this.personIdResponsible = personIdResponsible;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public List<EventPlaceDTO> getPlaces() {
		return places;
	}
	public void setPlaces(List<EventPlaceDTO> places) {
		this.places = places;
	}
	
}
