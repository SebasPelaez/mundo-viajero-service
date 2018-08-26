package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "State")
public class State implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "codigo")
    @SequenceGenerator(name = "codigo", sequenceName = "SQ_STATE", allocationSize = 1)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Description")
    private String description;
    
    @Column(name = "BelongsTo")
    private String belongsTo;

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

	public String getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(String belongsTo) {
		this.belongsTo = belongsTo;
	}
        
}


