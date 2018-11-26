package com.co.mundoviajero.dto.EventRecomendation;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateEventRecomendationDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @NotNull
    private Long eventId;
    
    @NotNull
    @NotEmpty
    private List<Long> recomendationsId;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public List<Long> getRecomendationsId() {
		return recomendationsId;
	}

	public void setRecomendationsId(List<Long> recomendationsId) {
		this.recomendationsId = recomendationsId;
	}

}
