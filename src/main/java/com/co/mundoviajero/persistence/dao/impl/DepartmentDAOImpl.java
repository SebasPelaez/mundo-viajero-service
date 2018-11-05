package com.co.mundoviajero.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.persistence.dao.IDepartmentDAO;
import com.co.mundoviajero.persistence.entity.Department;

@Repository(value = "DepartmentDAOImpl")
@Transactional
public class DepartmentDAOImpl extends BaseDAO implements IDepartmentDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getAllDeparments() {
		Query query = getCurrentSession().createQuery("From Department");
		List<Department> departments = (List<Department>)query.getResultList();
	    return departments;
	}

	@Override
	public Department getDepartment(Long id) {
		return getCurrentSession().find(Department.class, id);
	}

}
