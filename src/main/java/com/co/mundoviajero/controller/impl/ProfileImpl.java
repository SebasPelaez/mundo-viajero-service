package com.co.mundoviajero.controller.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.Profile.ProfileBusiness;
import com.co.mundoviajero.controller.ProfileController;
import com.co.mundoviajero.dto.ResponseDTO;

@RestController
public class ProfileImpl implements ProfileController {

	@Autowired
	private ProfileBusiness profileBusiness;
	
	@Override
	public ResponseEntity<ResponseDTO> getAllProfiles() throws Exception {
		return profileBusiness.getAllProfiles();
	}

	@Override
	public ResponseEntity<ResponseDTO> getProfile(@PathVariable("search") String search) throws Exception {
		if(StringUtils.isNotBlank(search) && StringUtils.isNumeric(search)) {
			return profileBusiness.getProfile(Long.parseLong(search));
		}		
		return null;
	}

}
