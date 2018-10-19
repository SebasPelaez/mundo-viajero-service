package com.co.mundoviajero.dto.event.eventplace;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateEventPlaceDTO extends BaseEventPlaceDTO{
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Length(max = 5)
	private String cityId;

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

}
