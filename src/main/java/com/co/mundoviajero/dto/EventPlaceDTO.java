package com.co.mundoviajero.dto;

import java.io.Serializable;

public class EventPlaceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long eventId;
	private Long cityId;
	private String eventPlaceStartDate;
	private String eventPlaceEndDate;
	private String altitudeEventPlace;
	private String latitudeEventPlace;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getEventPlaceStartDate() {
		return eventPlaceStartDate;
	}
	public void setEventPlaceStartDate(String eventPlaceStartDate) {
		this.eventPlaceStartDate = eventPlaceStartDate;
	}
	public String getEventPlaceEndDate() {
		return eventPlaceEndDate;
	}
	public void setEventPlaceEndDate(String eventPlaceEndDate) {
		this.eventPlaceEndDate = eventPlaceEndDate;
	}
	public String getAltitudeEventPlace() {
		return altitudeEventPlace;
	}
	public void setAltitudeEventPlace(String altitudeEventPlace) {
		this.altitudeEventPlace = altitudeEventPlace;
	}
	public String getLatitudeEventPlace() {
		return latitudeEventPlace;
	}
	public void setLatitudeEventPlace(String latitudeEventPlace) {
		this.latitudeEventPlace = latitudeEventPlace;
	}
	
}