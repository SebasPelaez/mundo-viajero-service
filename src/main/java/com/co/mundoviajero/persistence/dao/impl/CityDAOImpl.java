package com.co.mundoviajero.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.persistence.dao.ICityDAO;
import com.co.mundoviajero.persistence.entity.City;
import com.co.mundoviajero.persistence.entity.Department;

@Repository(value = "CityDAOImpl")
@Transactional
public class CityDAOImpl extends BaseDAO implements ICityDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getAllCities() {
		Query query = getCurrentSession().createQuery("From City");
		List<City> cities = (List<City>)query.getResultList();
	    return cities;
	}

	@Override
	public City getCity(Long id) {
		return getCurrentSession().find(City.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getDepartmentCities(Long departmentId) {
		
		Department department = new Department();
		department.setId(departmentId);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("select c from City c where c.departmentId = :department");
		
		Query query = getCurrentSession().createQuery(stringBuffer.toString());
		query.setParameter("department",department);
		
		List<City> cities = (List<City>) query.getResultList();
				
		return cities;
	}

}
