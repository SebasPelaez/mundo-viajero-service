package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Profile")
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "codigo")
    @SequenceGenerator(name = "codigo", sequenceName = "SQ_PROFILE", allocationSize = 1)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "StateId", referencedColumnName = "Id")
    private State state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description.trim();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
    
}
