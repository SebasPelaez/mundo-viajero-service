package com.co.mundoviajero.dto.event;

import java.io.Serializable;

import com.co.mundoviajero.persistence.entity.City;

public class EventPlaceDTO extends BaseEventPlaceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private City city;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City cityId) {
		this.city = cityId;
	}	
}