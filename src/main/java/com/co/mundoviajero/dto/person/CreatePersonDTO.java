package com.co.mundoviajero.dto.person;

public class CreatePersonDTO extends BasePersonDTO {

	private static final long serialVersionUID = 1L;

	private String profileId;
	private String stateId;
	
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
