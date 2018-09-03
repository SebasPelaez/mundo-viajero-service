package com.co.mundoviajero.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.dto.StateDTO;
import com.co.mundoviajero.persistence.dao.IStateDAO;
import com.co.mundoviajero.persistence.entity.State;

@Repository(value = "StateDAOImpl")
@Transactional
public class StateDAOImpl extends BaseDAO implements IStateDAO{

	@Override
	public List<State> getAllStates() {			
		Query query = getCurrentSession().createQuery("From State");
	    return (List<State>)query.getResultList();
	}

	@Override
	public State getState(Long id) {
		Query query = getCurrentSession().createQuery("select s from State s where s.id = :id");
		query.setParameter("id", id);
		return (State) query.getSingleResult();
	}

}
