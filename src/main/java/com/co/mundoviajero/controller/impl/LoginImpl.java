package com.co.mundoviajero.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.co.mundoviajero.business.Login.LoginBusiness;
import com.co.mundoviajero.controller.LoginController;
import com.co.mundoviajero.dto.ResponseDTO;

public class LoginImpl implements LoginController{
	
	@Autowired
	private LoginBusiness loginBusiness;

	@Override
	public ResponseEntity<ResponseDTO> getPersonWithParameters(@RequestParam Map<String, String> parameters) throws Exception {
		if(!parameters.isEmpty()) {
			return loginBusiness.getPersonWithParameters(parameters);
		}
		return null;
	}
}