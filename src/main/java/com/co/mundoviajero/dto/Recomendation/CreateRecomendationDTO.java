package com.co.mundoviajero.dto.Recomendation;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CreateRecomendationDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty
	@Length(max = 200)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
