package com.co.mundoviajero.dto.department;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DepartmentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Length(max = 5)
	private Long id;

	@NotNull
	@NotEmpty
	@Length(max = 150)
	private String name;
	
	public DepartmentDTO(Long id,String name) {
		this.id = id;
		this.name = name;
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

	
}
