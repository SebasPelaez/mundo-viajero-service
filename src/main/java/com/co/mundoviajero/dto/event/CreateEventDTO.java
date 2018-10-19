package com.co.mundoviajero.dto.event;

import com.co.mundoviajero.dto.event.eventplace.CreateEventPlaceDTO;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateEventDTO extends BaseEventDTO {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Length(max = 5)
	private String personIdResponsible;

	@NotNull
	@NotEmpty
	@Length(max = 5)
	private String stateId;

	@NotNull
	@NotEmpty
	private List<CreateEventPlaceDTO> places;

	@NotNull
	@NotEmpty
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
