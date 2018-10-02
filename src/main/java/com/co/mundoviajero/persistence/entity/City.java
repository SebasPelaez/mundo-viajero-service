package com.co.mundoviajero.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "City")
public class City {
	
	@Id
    @GeneratedValue(generator = "codigo")
    @SequenceGenerator(name = "codigo", sequenceName = "SQ_CITY", allocationSize = 1)
    @Column(name = "Id")
    private Long id;
	
	@Column(name = "Name")
    private String name;
    
    @Column(name = "DepartmentId")
    private String departmentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}
