package com.co.mundoviajero.dto.event.eventplace;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public abstract class BaseEventPlaceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Length(max = 5)
	protected Long eventId;

	@NotNull
	@NotEmpty
	@Length(max = 25)
	protected String eventPlaceStartDate;

	@NotNull
	@NotEmpty
	@Length(max = 25)
	protected String eventPlaceEndDate;

	@NotNull
	@NotEmpty
	@Length(max = 15)
	protected String longitudeEventPlace;

	@NotNull
	@NotEmpty
	@Length(max = 15)
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
