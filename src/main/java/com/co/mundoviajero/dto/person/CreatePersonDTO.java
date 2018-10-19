package com.co.mundoviajero.dto.person;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreatePersonDTO extends BasePersonDTO {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Length(max = 5)
	private String profileId;

	@NotNull
	@NotEmpty
	@Length(max = 5)
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
