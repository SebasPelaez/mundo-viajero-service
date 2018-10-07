package com.co.mundoviajero.dto.event;

import java.io.Serializable;

public abstract class BaseEventDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Long id;
	protected String name;
	protected String description;
	protected String startDate;
	protected String endDate;
	protected String longitudeMeetingPoint;
	protected String latitudeMeetingPoint;
	protected int capaciticy;
	protected float fare;
	
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
	public String getLongitudeMeetingPoint() {
		return longitudeMeetingPoint;
	}
	public void setLongitudeMeetingPoint(String longitudeMeetingPoint) {
		this.longitudeMeetingPoint = longitudeMeetingPoint;
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
	
}
