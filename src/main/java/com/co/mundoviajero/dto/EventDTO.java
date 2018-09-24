package com.co.mundoviajero.dto;

import java.io.Serializable;
import java.util.List;

public class EventDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private String startDate;
	private String endDate;
	private String altitudeMeetingPoint;
	private String latitudeMeetingPoint;
	private int capaciticy;
	private float fare;
	private Long personIdResponsible;
	private Long stateId;
	private List<EventPlaceDTO> places;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAltitudeMeetingPoint() {
		return altitudeMeetingPoint;
	}
	public void setAltitudeMeetingPoint(String altitudeMeetingPoint) {
		this.altitudeMeetingPoint = altitudeMeetingPoint;
	}
	public String getLatitudeMeetingPoint() {
		return latitudeMeetingPoint;
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
	public Long getPersonIdResponsible() {
		return personIdResponsible;
	}
	public void setPersonIdResponsible(Long personIdResponsible) {
		this.personIdResponsible = personIdResponsible;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public List<EventPlaceDTO> getPlaces() {
		return places;
	}
	public void setPlaces(List<EventPlaceDTO> places) {
		this.places = places;
	}
	
}
