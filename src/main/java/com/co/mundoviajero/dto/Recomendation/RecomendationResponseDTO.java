package com.co.mundoviajero.dto.Recomendation;

import java.io.Serializable;

public class RecomendationResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String description;
    
	public RecomendationResponseDTO(Long id, String description) {
		this.id = id;
		this.description = description;
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
    
    

}
