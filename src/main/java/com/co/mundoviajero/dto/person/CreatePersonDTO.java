package com.co.mundoviajero.dto.person;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreatePersonDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Length(max = 20)
	private String identification;

	@Length(max = 25)
	private String rnt;

	@NotNull
	@NotEmpty
	@Length(max = 100)
	private String name;

	@NotNull
	@NotEmpty
	@Length(max = 100)
	private String lastName;

	@NotNull
	@NotEmpty
	@Length(max = 12)
	private String birthday;

	@NotNull
	@NotEmpty
	@Length(max = 40)
	private String email;

	@NotNull
	@NotEmpty
	@Length(max = 13)
	private String phoneNumber;

	@Length(max = 150)
	private String address;

	@Length(max = 500)
	private String password;

	@Length(max = 4)
	private Double calification;

	@Length(max = 150)
	private String token;

	@Length(max = 500)
	private String profilePhoto;
	
	@NotNull
	@NotEmpty
	@Length(max = 5)
	private String profileId;

	@NotNull
	@NotEmpty
	@Length(max = 5)
	private String stateId;
	
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
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
}
