package com.co.mundoviajero.dto.event;

import java.io.Serializable;

public abstract class BaseEventPlaceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Long eventId;
	protected String eventPlaceStartDate;
	protected String eventPlaceEndDate;
	protected String longitudeEventPlace;
	protected String latitudeEventPlace;
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
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
	public String getLongitudeEventPlace() {
		return longitudeEventPlace;
	}
	public void setLongitudeEventPlace(String longitudeEventPlace) {
		this.longitudeEventPlace = longitudeEventPlace;
	}
	public String getLatitudeEventPlace() {
		return latitudeEventPlace;
	}
	public void setLatitudeEventPlace(String latitudeEventPlace) {
		this.latitudeEventPlace = latitudeEventPlace;
	}

}
