package com.co.mundoviajero.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.Department.DepartmentBusiness;
import com.co.mundoviajero.controller.DepartmentController;
import com.co.mundoviajero.dto.ResponseDTO;

@RestController
public class DepartmentImpl implements DepartmentController{
	
	@Autowired
	private DepartmentBusiness departmentBusiness;
	
	@Override
	public ResponseEntity<ResponseDTO> getAllDepartments() throws Exception {
		return departmentBusiness.getAllDepartments();
	}

	@Override
	public ResponseEntity<ResponseDTO> getDepartment(@PathVariable("id") Long id) throws Exception {
		return departmentBusiness.getDepartment(id);
	}

}
