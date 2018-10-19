package com.co.mundoviajero.dto.event.eventplace;

import java.io.Serializable;

import com.co.mundoviajero.persistence.entity.City;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EventPlaceDTO extends BaseEventPlaceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Length(max = 5)
	private Long id;

	@NotNull
	@NotEmpty
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