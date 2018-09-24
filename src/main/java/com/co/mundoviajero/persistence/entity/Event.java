package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Event")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	// Attributes
	@Id
	@GeneratedValue(generator = "codigo")
	@SequenceGenerator(name = "codigo", sequenceName = "SQ_EVENT", allocationSize = 1)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "Name")
    private String name;
	
	@Column(name = "Description")
    private String description;
	
	@Column(name = "StartDate")
    private Date startDate;
	
	@Column(name = "EndDate")
    private Date endDate;
	
	@Column(name = "AltitudeMeetingPoint")
    private String altitudeMeetingPoint;
	
	@Column(name = "LatitudeMeetingPoint")
    private String latitudeMeetingPoint;
	
	@Column(name = "Capaciticy")
    private int capaciticy;
	
	@Column(name = "Fare")
    private float fare;
	
	@Column(name = "PersonIdResponsible")
    private Long personIdResponsible;
	
	@Column(name = "StateId")
    private Long stateId;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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

}
