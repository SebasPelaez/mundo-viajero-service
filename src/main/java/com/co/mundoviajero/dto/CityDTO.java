package com.co.mundoviajero.dto;

import java.io.Serializable;

public class CityDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private DepartmentDTO department;
	
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
	public DepartmentDTO getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}

}
