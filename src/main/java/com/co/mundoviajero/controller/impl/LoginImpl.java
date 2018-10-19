package com.co.mundoviajero.controller.impl;

import java.util.Map;

import com.co.mundoviajero.dto.login.AuthenticateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
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

	@Autowired
	private MessageSourceAccessor messageSource;

	@Override
	public ResponseEntity<ResponseDTO> login(@RequestBody AuthenticateDTO authenticateParameters) throws Exception {
		return loginBusiness.login(authenticateParameters);
	}
}
