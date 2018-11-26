package com.co.mundoviajero.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.persistence.dao.IRecomendationDAO;
import com.co.mundoviajero.persistence.entity.Recomendation;

@Repository(value = "RecomendationDAOImpl")
@Transactional
public class RecomendationDAOImpl  extends BaseDAO implements IRecomendationDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Recomendation> getAllRecomendations() {
		List<Recomendation> recomendations = new ArrayList<>();
		Query query = getCurrentSession().createQuery("From Recomendation");
		recomendations = (List<Recomendation>) query.getResultList();
		return recomendations;
	}

	@Override
	public Recomendation getRecomendation(Long id) {
		return getCurrentSession().find(Recomendation.class, id);
	}

	@Override
	public boolean createRecomendation(Recomendation recomendation) {
		boolean succes = true;
		try {
			getCurrentSession().saveOrUpdate(recomendation);
		} catch (Exception e) {
			succes = false;
		}
		return succes;
	}

	
	
}
