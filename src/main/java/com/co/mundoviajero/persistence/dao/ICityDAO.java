package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.persistence.entity.City;

public interface ICityDAO {
	
	List<City> getAllCities();
	City getCity(Long id);
	List<City> getDepartmentCities(Long id);

}
