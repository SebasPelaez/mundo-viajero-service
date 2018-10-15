package com.co.mundoviajero.dto.person;

import com.co.mundoviajero.persistence.entity.Profile;
import com.co.mundoviajero.persistence.entity.State;

public class PersonDTO extends BasePersonDTO {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Profile profileId;
	private State stateId;
	
	public Long getId() {
    	return id;
    }    
    public void setId (Long id) {
    	this.id = id;
    }    
	public Profile getProfile() {
		return profileId;
	}
	public void setProfile(Profile profile) {
		this.profileId = profile;
	}
	public State getState() {
		return stateId;
	}
	public void setState(State state) {
		this.stateId = state;
	}
	

}
