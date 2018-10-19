package com.co.mundoviajero.dto.person;

import com.co.mundoviajero.dto.profile.ProfileResponseDTO;
import com.co.mundoviajero.dto.state.StateResponseDTO;
import com.co.mundoviajero.persistence.entity.Profile;
import com.co.mundoviajero.persistence.entity.State;

import java.io.Serializable;

public class PersonResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String identification;
    private String rnt;
    private String name;
    private String lastName;
    private String birthday;
    private String email;
    private String phoneNumber;
    private String address;
    private Double calification;
    private String profilePhoto;
    private String token;
    private ProfileResponseDTO profile;
    private StateResponseDTO state;

    public PersonResponseDTO(Long id, String identification, String rnt, String name, String lastName, String birthday,
                             String email, String phoneNumber, String address, Double calification, String profilePhoto,
                             String token, ProfileResponseDTO profile, StateResponseDTO state) {
        this.id = id;
        this.identification = identification;
        this.rnt = rnt;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.calification = calification;
        this.profilePhoto = profilePhoto;
        this.token = token;
        this.profile = profile;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getCalification() {
        return calification;
    }

    public void setCalification(Double calification) {
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

    public ProfileResponseDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileResponseDTO profile) {
        this.profile = profile;
    }

    public StateResponseDTO getState() {
        return state;
    }

    public void setState(StateResponseDTO state) {
        this.state = state;
    }
}
