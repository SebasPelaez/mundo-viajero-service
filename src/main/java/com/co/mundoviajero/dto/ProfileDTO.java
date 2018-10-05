package com.co.mundoviajero.dto;

import java.io.Serializable;

import com.co.mundoviajero.persistence.entity.State;

public class ProfileDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String description;
	private State state;
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
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	

}
