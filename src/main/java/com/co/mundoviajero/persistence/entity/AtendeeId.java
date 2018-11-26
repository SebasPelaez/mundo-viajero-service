package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class AtendeeId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "PersonId")
	private Long personId;
	
	@Column(name = "EventId")
	private Long eventId;

	public AtendeeId(Long personId, Long eventId) {
		this.personId = personId;
		this.eventId = eventId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

}
