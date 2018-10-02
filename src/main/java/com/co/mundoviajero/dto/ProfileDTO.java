package com.co.mundoviajero.dto;

import java.io.Serializable;

public class ProfileDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String description;
	private StateDTO state;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StateDTO getState() {
		return state;
	}
	public void setState(StateDTO state) {
		this.state = state;
	}
	
	

}
