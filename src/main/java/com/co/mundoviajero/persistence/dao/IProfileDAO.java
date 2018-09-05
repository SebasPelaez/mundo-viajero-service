package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.dto.ProfileDTO;

public interface IProfileDAO {

	List<ProfileDTO> getAllProfiles();
	ProfileDTO getProfile(Long id);
	
}
