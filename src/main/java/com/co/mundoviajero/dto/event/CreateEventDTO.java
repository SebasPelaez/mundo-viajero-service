package com.co.mundoviajero.dto.event;

import java.util.List;

public class CreateEventDTO extends BaseEventDTO {
	
	private static final long serialVersionUID = 1L;
	
	private String personIdResponsible;
	private String stateId;
	private List<CreateEventPlaceDTO> places;
	private List<String> images;
	
	public String getPersonIdResponsible() {
		return personIdResponsible;
	}
	public void setPersonIdResponsible(String personIdResponsible) {
		this.personIdResponsible = personIdResponsible;
	}
	public String getStateId() {
		return stateId;
	}
	public void setState(String stateId) {
		this.stateId = stateId;
	}
	public List<CreateEventPlaceDTO> getPlaces() {
		return places;
	}
	public void setPlaces(List<CreateEventPlaceDTO> places) {
		this.places = places;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public List<String> getImages() {return images;}
	public void setImages(List<String> images) {this.images = images;}
}
