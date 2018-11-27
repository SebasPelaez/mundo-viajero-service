package com.co.mundoviajero.dto.event;

import com.co.mundoviajero.dto.event.eventplace.CreateEventPlaceDTO;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

public class CreateEventDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Length(max = 150)
	protected String name;

	@NotNull
	@NotEmpty
	@Length(max = 500)
	protected String description;

	@NotNull
	@NotEmpty
	@Length(max = 24)
	protected String startDate;

	@NotNull
	@NotEmpty
	@Length(max = 24)
	protected String endDate;

	@NotNull
	@NotEmpty
	@Length(max = 250)
	protected String longitudeMeetingPoint;

	@NotNull
	@NotEmpty
	@Length(max = 250)
	protected String latitudeMeetingPoint;

	@NotNull
	protected int capaciticy;

	@NotNull
	protected float fare;
	
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
	
	@NotNull
	@NotEmpty
	private List<Long> recomendations;
	
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
	
	public String getName() {
		return name.trim();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description.trim();
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate.replace("T", " ");
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate.replace("T", " ");
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLongitudeMeetingPoint() {
		return longitudeMeetingPoint.trim();
	}
	public void setLongitudeMeetingPoint(String longitudeMeetingPoint) {
		this.longitudeMeetingPoint = longitudeMeetingPoint;
	}
	public String getLatitudeMeetingPoint() {
		return latitudeMeetingPoint.trim();
	}
	public void setLatitudeMeetingPoint(String latitudeMeetingPoint) {
		this.latitudeMeetingPoint = latitudeMeetingPoint;
	}
	public int getCapaciticy() {
		return capaciticy;
	}
	public void setCapaciticy(int capaciticy) {
		this.capaciticy = capaciticy;
	}
	public float getFare() {
		return fare;
	}
	public void setFare(float fare) {
		this.fare = fare;
	}
	public List<Long> getRecomendations() {
		return recomendations;
	}
	public void setRecomendations(List<Long> recomendations) {
		this.recomendations = recomendations;
	}
	
	
}
