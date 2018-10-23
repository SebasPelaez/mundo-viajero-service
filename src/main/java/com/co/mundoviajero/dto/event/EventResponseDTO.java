package com.co.mundoviajero.dto.event;

import java.io.Serializable;
import java.util.List;

import com.co.mundoviajero.dto.event.eventplace.EventPlaceDTO;
import com.co.mundoviajero.dto.event.imageevent.ImageEventResponseDTO;
import com.co.mundoviajero.dto.person.PersonResponseDTO;
import com.co.mundoviajero.dto.state.StateResponseDTO;

public class EventResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private String startDate;
	private String endDate;
	private double longitudeMeetingPoint;
	private double latitudeMeetingPoint;
	private int capaciticy;
	private float fare;
	private PersonResponseDTO personResponsible;
	private StateResponseDTO state;
	private List<EventPlaceDTO> places;
	private List<ImageEventResponseDTO> images;
	
	
	
	public EventResponseDTO(Long id, String name, String description, String startDate, String endDate,
			double longitudeMeetingPoint, double latitudeMeetingPoint, int capaciticy, float fare,
			PersonResponseDTO personResponsible, StateResponseDTO state, List<EventPlaceDTO> places, List<ImageEventResponseDTO> images) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.longitudeMeetingPoint = longitudeMeetingPoint;
		this.latitudeMeetingPoint = latitudeMeetingPoint;
		this.capaciticy = capaciticy;
		this.fare = fare;
		this.personResponsible = personResponsible;
		this.state = state;
		this.places = places;
		this.images = images;
	}
	
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
	public double getLongitudeMeetingPoint() {
		return longitudeMeetingPoint;
	}
	public void setLongitudeMeetingPoint(double longitudeMeetingPoint) {
		this.longitudeMeetingPoint = longitudeMeetingPoint;
	}
	public double getLatitudeMeetingPoint() {
		return latitudeMeetingPoint;
	}
	public void setLatitudeMeetingPoint(double latitudeMeetingPoint) {
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
	public PersonResponseDTO getPersonResponsible() {
		return personResponsible;
	}
	public void setPersonResponsible(PersonResponseDTO personResponsible) {
		this.personResponsible = personResponsible;
	}
	public StateResponseDTO getState() {
		return state;
	}
	public void setState(StateResponseDTO state) {
		this.state = state;
	}
	public List<EventPlaceDTO> getPlaces() {
		return places;
	}
	public void setPlaces(List<EventPlaceDTO> places) {
		this.places = places;
	}
	public List<ImageEventResponseDTO> getImages() {
		return images;
	}
	public void setImages(List<ImageEventResponseDTO> images) {
		this.images = images;
	}
}
