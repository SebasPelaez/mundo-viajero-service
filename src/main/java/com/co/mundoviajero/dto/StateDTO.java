package com.co.mundoviajero.dto;

import java.io.Serializable;

public class StateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String description;
	private String belongsTo;
	
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
