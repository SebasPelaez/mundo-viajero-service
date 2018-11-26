package com.co.mundoviajero.dto.EventRecomendation;

import java.io.Serializable;

import com.co.mundoviajero.dto.Recomendation.RecomendationResponseDTO;

public class EventRecomendationResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long eventId;
	private RecomendationResponseDTO recomendation;
	
	public EventRecomendationResponseDTO(Long eventId, RecomendationResponseDTO recomendation) {
		super();
		this.eventId = eventId;
		this.recomendation = recomendation;
	}
	
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public RecomendationResponseDTO getRecomendation() {
		return recomendation;
	}
	public void setRecomendation(RecomendationResponseDTO recomendation) {
		this.recomendation = recomendation;
	}

}
