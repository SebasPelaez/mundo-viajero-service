package com.co.mundoviajero.dto;

import java.io.Serializable;

public class ProfileDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String description;
	private Long stateId;
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
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
	

}
