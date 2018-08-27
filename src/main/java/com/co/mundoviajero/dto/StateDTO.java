package com.co.mundoviajero.dto;

import java.io.Serializable;

public class StateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String description;
	private String belongsTo;

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
