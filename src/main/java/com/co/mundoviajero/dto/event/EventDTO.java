package com.co.mundoviajero.dto.event;

import java.util.List;

import com.co.mundoviajero.dto.event.eventplace.EventPlaceDTO;
import com.co.mundoviajero.dto.event.imageevent.ImageEventDTO;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.persistence.entity.State;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EventDTO extends BaseEventDTO{

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	private Person personIdResponsible;

	@NotNull
	@NotEmpty
	private State state;

	@NotNull
	@NotEmpty
	private List<EventPlaceDTO> places;

	@NotNull
	@NotEmpty
	private List<ImageEventDTO> images;
	
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
	public List<ImageEventDTO> getImages() {
		return images;
	}
	public void setImages(List<ImageEventDTO> images) {
		this.images = images;
	}
}
