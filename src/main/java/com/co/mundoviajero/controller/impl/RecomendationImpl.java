package com.co.mundoviajero.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.Recomendation.RecomendationBusiness;
import com.co.mundoviajero.controller.RecomendationCrontroller;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.Recomendation.CreateRecomendationDTO;

@RestController
public class RecomendationImpl implements RecomendationCrontroller {

	@Autowired
	private RecomendationBusiness recomendationBusiness;
	
	@Override
	public ResponseEntity<ResponseDTO> getAllRecomendations() throws Exception {
		return recomendationBusiness.getAllRecomendations();
	}

	@Override
	public ResponseEntity<ResponseDTO> getRecomendation(@PathVariable("id") Long id) throws Exception {
		return recomendationBusiness.getRecomendation(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> createRecomendation(@Valid @RequestBody CreateRecomendationDTO recomendation)
			throws Exception {
		return recomendationBusiness.createRecomendation(recomendation);
	}

}
