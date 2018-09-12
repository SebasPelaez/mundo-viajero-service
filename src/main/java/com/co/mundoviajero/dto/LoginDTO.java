package com.co.mundoviajero.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private PersonDTO person;
	private String token;	
	
	public LoginDTO(PersonDTO person, String token) {
		super();
		this.person = person;
		this.token = token;
	}
	
	public PersonDTO getPersonDTO() {
		return person;
	}
	public void setPersonDTO(PersonDTO person) {
		this.person = person;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
