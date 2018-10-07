package com.co.mundoviajero.dto.event;

public class CreateEventPlaceDTO extends BaseEventPlaceDTO{
	
	private static final long serialVersionUID = 1L;
	
	private String cityId;

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

}
