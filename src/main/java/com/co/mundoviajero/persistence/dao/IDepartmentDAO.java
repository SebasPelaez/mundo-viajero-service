package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.persistence.entity.Department;

public interface IDepartmentDAO {
	
	List<Department> getAllDeparments();
	Department getDepartment(Long id);

}
