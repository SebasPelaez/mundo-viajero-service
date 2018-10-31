package com.co.mundoviajero.dto.profile;

import java.io.Serializable;

import com.co.mundoviajero.dto.state.StateResponseDTO;

public class ProfileResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String description;
	private StateResponseDTO state;

	public ProfileResponseDTO(Long id, String description, StateResponseDTO state) {
		this.id = id;
		this.description = description;
		this.state = state;
	}

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
	public StateResponseDTO getState() {
		return state;
	}
	public void setState(StateResponseDTO state) {
		this.state = state;
	}

}
