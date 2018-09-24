package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EventPlace")
public class EventPlace implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributes
	@Id
	@GeneratedValue(generator = "codigo")
	@SequenceGenerator(name = "codigo", sequenceName = "SQ_EVENTPLACE", allocationSize = 1)
	@Column(name = "Id")
	private Long id;

	@Column(name = "EventId")
	private Long eventId;

	@Column(name = "CityId")
	private Long cityId;

	@Column(name = "EventPlaceStartDate")
	private Timestamp eventPlaceStartDate;

	@Column(name = "EventPlaceEndDate")
	private Timestamp eventPlaceEndDate;

	@Column(name = "AltitudeEventPlace")
	private String altitudeEventPlace;

	@Column(name = "LatitudeEventPlace")
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

	public Timestamp getEventPlaceStartDate() {
		return eventPlaceStartDate;
	}

	public void setEventPlaceStartDate(Timestamp eventPlaceStartDate) {
		this.eventPlaceStartDate = eventPlaceStartDate;
	}

	public Timestamp getEventPlaceEndDate() {
		return eventPlaceEndDate;
	}

	public void setEventPlaceEndDate(Timestamp eventPlaceEndDate) {
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
