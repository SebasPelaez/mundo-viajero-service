package com.co.mundoviajero.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Department")
public class Department {
	
	@Id
    @GeneratedValue(generator = "codigo")
    @SequenceGenerator(name = "codigo", sequenceName = "SQ_DEPARTMENT", allocationSize = 1)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name.trim();
	}

	public void setName(String name) {
		this.name = name;
	}

}
