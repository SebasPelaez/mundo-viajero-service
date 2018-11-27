package com.co.mundoviajero.dto.event.eventplace;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateEventPlaceDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotNull
	private Long eventId;
	
	@NotNull
	@NotEmpty
	@Length(max = 100)
	private String name;

	@NotNull
	@NotEmpty
	@Length(max = 5)
	private String cityId;

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

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getEventPlaceStartDate() {
		return eventPlaceStartDate.replace("T", " ");
	}

	public void setEventPlaceStartDate(String eventPlaceStartDate) {
		this.eventPlaceStartDate = eventPlaceStartDate;
	}

	public String getEventPlaceEndDate() {
		return eventPlaceEndDate.replace("T", " ");
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
	
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
