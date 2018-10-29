package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.persistence.entity.Profile;

public interface IProfileDAO {

	List<Profile> getAllProfiles();
	Profile getProfile(Long id);
	
}
