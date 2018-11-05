package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column(name = "Name")
	private String name;
	
	@Column(name = "EventId")
	private Long eventId;

	@ManyToOne
    @JoinColumn(name = "CityId", referencedColumnName = "Id")
	private City cityId;

	@Column(name = "EventPlaceStartDate")
	private Timestamp eventPlaceStartDate;

	@Column(name = "EventPlaceEndDate")
	private Timestamp eventPlaceEndDate;

	@Column(name = "LongitudeEventPlace")
	private double longitudeEventPlace;

	@Column(name = "LatitudeEventPlace")
	private double latitudeEventPlace;

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

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public City getCityId() {
		return cityId;
	}

	public void setCityId(City cityId) {
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
	
}
