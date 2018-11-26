package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EventRecomendation")
public class EventRecomendation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private EventRecomendationId primary;

	public EventRecomendationId getPrimary() {
		return primary;
	}

	public void setPrimary(EventRecomendationId primary) {
		this.primary = primary;
	}

}
