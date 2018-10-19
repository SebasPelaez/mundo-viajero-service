package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
    // Attributes	
	@Id
    @GeneratedValue(generator = "codigo")
    @SequenceGenerator(name = "codigo", sequenceName = "SQ_PERSON", allocationSize = 1)
    @Column(name = "Id")
    private Long id;
    
    @Column(name = "Identification")
    private String identification;

    @Column(name = "RNT")
    private String rnt;
    
    @Column(name = "Name")
    private String name;
    
    @Column(name = "LastName")
    private String lastName;
    
    @Column(name = "Birthday")
    private Date birthday;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    
    @Column(name = "Address")
    private String address;
    
    @Column(name = "Password")
    private String password;
    
    @Column(name = "Calification")
    private double calification;
    
    @Column(name = "ProfilePhoto")
    private String profilePhoto;
    
    @Column(name = "Token")
    private String token;
    
    @ManyToOne
    @JoinColumn(name = "ProfileId", referencedColumnName = "Id")
    private Profile profile;
    
    @ManyToOne
    @JoinColumn(name = "StateId", referencedColumnName = "Id")
    private State state;
    
    // Constructor and Methods
    public Person() {}
    
    public Long getId() {
    	return id;
    }
    
    public void setId (Long id) {
    	this.id = id;
    }
    
    public String getIdentification() {
    	return identification;
    }
    
    public void setIdentification(String identification) {
    	this.identification = identification;
    }
    
    public String getRNT() {
    	return rnt;
    }
    
    public void setRNT(String rnt) {
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
    
    public Date getBirthday() {
    	return birthday;
    }
    
    public void setBirthday(Date birthday) {
    	this.birthday = birthday;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email)
    {
    	this.email = email;// End Constructor and Methods
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
    
    public double getCalification() {
    	return calification;
    }
    
    public void setCalification(double calification) {
    	this.calification = calification;
    }
    
    public String getProfilePhoto() {
    	return profilePhoto;
    }
    
    public void setProfilePhoto(String profilePhoto) {
    	this.profilePhoto = profilePhoto;
    }
    
    public String getToken() {
    	return token;
    }
    
    public void setToken(String token) {
    	this.token = token;
    }
    
    public Profile getProfile() {
    	return profile;
    }
    
    public void setProfile(Profile profile) {
    	this.profile = profile;
    }
    
    public State getState() {
    	return state;
    }
    
    public void setState(State state) {
    	this.state = state;
    }
}
