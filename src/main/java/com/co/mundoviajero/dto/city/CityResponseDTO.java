package com.co.mundoviajero.dto.city;

import com.co.mundoviajero.dto.department.DepartmentResponseDTO;
import java.io.Serializable;

public class CityResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private DepartmentResponseDTO department;
	
	public CityResponseDTO(Long id,String name,DepartmentResponseDTO department) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
	}
	
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
	public DepartmentResponseDTO getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentResponseDTO department) {
		this.department = department;
	}

}
