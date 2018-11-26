package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class EventRecomendationId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "EventId")
	private Long eventId;
	
	@ManyToOne
    @JoinColumn(name = "RecomendationId", referencedColumnName = "Id")
	private Recomendation recomendation;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Recomendation getRecomendation() {
		return recomendation;
	}

	public void setRecomendation(Recomendation recomendation) {
		this.recomendation = recomendation;
	}

	public EventRecomendationId(Long eventId, Recomendation recomendation) {
		this.eventId = eventId;
		this.recomendation = recomendation;
	}
	
	public EventRecomendationId() {
	}

}
