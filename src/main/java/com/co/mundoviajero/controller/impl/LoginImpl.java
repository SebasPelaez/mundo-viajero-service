package com.co.mundoviajero.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.Login.LoginBusiness;
import com.co.mundoviajero.controller.LoginController;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.util.exception.ValidationException;

@RestController
public class LoginImpl implements LoginController{
	
	@Autowired
	private LoginBusiness loginBusiness;

	@Override
	public ResponseEntity<ResponseDTO> login(@RequestBody Map<String, String> loginParameters) throws Exception {
		if(!loginParameters.isEmpty()){
			return loginBusiness.login(loginParameters);
		}
		throw new ValidationException("El body esta vacio");
	}
}
