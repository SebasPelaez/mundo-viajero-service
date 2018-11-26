package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Atendee")
public class Atendee implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private AtendeeId primary;
	
	@Column(name = "EnrollDate")
	private Timestamp enrollDate;
	
	@ManyToOne
    @JoinColumn(name = "StateId", referencedColumnName = "Id")
    private State state;

	public AtendeeId getPrimary() {
		return primary;
	}

	public void setPrimary(AtendeeId primary) {
		this.primary = primary;
	}

	public Timestamp getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Timestamp enrollDate) {
		this.enrollDate = enrollDate;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
