package com.co.mundoviajero.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.co.mundoviajero.persistence.dao.IProfileDAO;
import com.co.mundoviajero.persistence.entity.Profile;

@Repository(value = "ProfileDAOImpl")
@Transactional
public class ProfileDAOImpl extends BaseDAO implements IProfileDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> getAllProfiles() {

		Query query = getCurrentSession().createQuery("From Profile");
		List<Profile> profiles = (List<Profile>)query.getResultList();
	    return profiles;
	}

	@Override
	public Profile getProfile(Long id) {
		return getCurrentSession().find(Profile.class, id);
	}

}
