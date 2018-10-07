package com.co.mundoviajero.dto.person;

import java.io.Serializable;

public abstract class BasePersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected String identification;
	protected String rnt;
	protected String name;
	protected String lastName;
	protected String birthday;
	protected String email;
	protected String phoneNumber;
	protected String address;
	protected String password;
	protected Double calification;
	protected String token;
	protected String profilePhoto;
	
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getRnt() {
		return rnt;
	}
	public void setRnt(String rnt) {
		this.rnt = rnt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getCalification() {
		return calification;
	}
	public void setCalification(Double calification) {
		this.calification = calification;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

}
