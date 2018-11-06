package com.co.mundoviajero.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.City.CityBusiness;
import com.co.mundoviajero.controller.CityController;
import com.co.mundoviajero.dto.ResponseDTO;

@RestController
public class CityImpl implements CityController{

	@Autowired
	private CityBusiness cityBusiness;
	
	@Override
	public ResponseEntity<ResponseDTO> getAllCities() throws Exception {
		return cityBusiness.getAllCities();
	}

	@Override
	public ResponseEntity<ResponseDTO> getCity(@PathVariable("id") Long id) throws Exception {
		return cityBusiness.getCity(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> getDepartmentCities(@PathVariable("id") Long id) throws Exception {
		return cityBusiness.getDepartmentCities(id);
	}

}
