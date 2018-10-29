package com.co.mundoviajero.dto.login;

import java.io.Serializable;

import com.co.mundoviajero.dto.person.PersonResponseDTO;

public class LoginResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private PersonResponseDTO person;
	private String token;	
	
	public LoginResponseDTO(PersonResponseDTO person, String token) {
		this.person = person;
		this.token = token;
	}
	
	public PersonResponseDTO getPersonDTO() {
		return person;
	}
	public void setPersonDTO(PersonResponseDTO person) {
		this.person = person;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
