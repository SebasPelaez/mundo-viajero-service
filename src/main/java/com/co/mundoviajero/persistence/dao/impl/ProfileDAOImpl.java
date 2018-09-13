package com.co.mundoviajero.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.dto.ProfileDTO;
import com.co.mundoviajero.persistence.dao.IProfileDAO;
import com.co.mundoviajero.persistence.entity.Profile;

@Repository(value = "ProfileDAOImpl")
@Transactional
public class ProfileDAOImpl extends BaseDAO implements IProfileDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<ProfileDTO> getAllProfiles() {
		List<ProfileDTO> profilesDTO = new ArrayList<>();
		Query query = getCurrentSession().createQuery("From Profile");
		List<Profile> profiles = (List<Profile>)query.getResultList();
		
		for(Profile p: profiles) {
			profilesDTO.add(setProfileDTO(p));
		}
		
	    return profilesDTO;
	}

	@Override
	public ProfileDTO getProfile(Long id) {
		Query query = getCurrentSession().createQuery("select p from Profile p where p.id = :id");
		query.setParameter("id", id);
		ProfileDTO profileDTO = setProfileDTO((Profile) query.getSingleResult());
		return profileDTO;
	}
	
	private ProfileDTO setProfileDTO(Profile profile) {
		
		ProfileDTO profileDTO = new ProfileDTO();
		profileDTO.setId(profile.getId());
		profileDTO.setDescription(profile.getDescription().trim());
		profileDTO.setStateId(profile.getStateId());
		
		return profileDTO;
	}

}
