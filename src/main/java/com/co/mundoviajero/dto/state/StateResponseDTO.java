package com.co.mundoviajero.dto.state;

import java.io.Serializable;

public class StateResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String description;
	private String belongsTo;

	public StateResponseDTO(Long id, String description, String belongsTo) {
		this.id = id;
		this.description = description;
		this.belongsTo = belongsTo;
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
	public String getBelongsTo() {
		return belongsTo;
	}
	public void setBelongsTo(String belongsTo) {
		this.belongsTo = belongsTo;
	}

}
