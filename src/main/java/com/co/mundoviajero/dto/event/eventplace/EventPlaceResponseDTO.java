package com.co.mundoviajero.dto.event.eventplace;

import com.co.mundoviajero.dto.city.CityDTO;
import java.io.Serializable;

public class EventPlaceResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long eventId;
	private String eventPlaceStartDate;
	private String eventPlaceEndDate;
	private double longitudeEventPlace;
	private double latitudeEventPlace;
	private CityDTO city;

	public EventPlaceResponseDTO(Long id, Long eventId, String eventPlaceStartDate, String eventPlaceEndDate,
			double longitudeEventPlace, double latitudeEventPlace, CityDTO city) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.eventPlaceStartDate = eventPlaceStartDate;
		this.eventPlaceEndDate = eventPlaceEndDate;
		this.longitudeEventPlace = longitudeEventPlace;
		this.latitudeEventPlace = latitudeEventPlace;
		this.city = city;
	}
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
	public double getLongitudeEventPlace() {
		return longitudeEventPlace;
	}
	public void setLongitudeEventPlace(double longitudeEventPlace) {
		this.longitudeEventPlace = longitudeEventPlace;
	}
	public double getLatitudeEventPlace() {
		return latitudeEventPlace;
	}
	public void setLatitudeEventPlace(double latitudeEventPlace) {
		this.latitudeEventPlace = latitudeEventPlace;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CityDTO getCity() {
		return city;
	}
	public void setCity(CityDTO city) {
		this.city = city;
	}

}
