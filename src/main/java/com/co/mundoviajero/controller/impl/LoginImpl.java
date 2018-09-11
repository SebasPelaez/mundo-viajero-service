package com.co.mundoviajero.controller.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.Login.LoginBusiness;
import com.co.mundoviajero.controller.LoginController;
import com.co.mundoviajero.dto.LoginDTO;
import com.co.mundoviajero.dto.ResponseDTO;

@RestController
public class LoginImpl implements LoginController{
	
	@Autowired
	private LoginBusiness loginBusiness;

	@Override
	public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO login) throws Exception {
		return loginBusiness.login(login);
	}
}
