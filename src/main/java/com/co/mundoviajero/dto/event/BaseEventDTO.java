package com.co.mundoviajero.dto.event;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public abstract class BaseEventDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Length(max = 5)
	protected Long id;

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
	@NotEmpty
	@Length(max = 4)
	protected int capaciticy;

	@NotNull
	@NotEmpty
	@Length(max = 8)
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
