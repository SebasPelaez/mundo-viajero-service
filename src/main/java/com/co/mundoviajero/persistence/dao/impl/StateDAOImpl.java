package com.co.mundoviajero.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.persistence.dao.IStateDAO;
import com.co.mundoviajero.persistence.entity.State;

@Repository(value = "StateDAOImpl")
@Transactional
public class StateDAOImpl extends BaseDAO implements IStateDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<State> getAllStates() {
		Query query = getCurrentSession().createQuery("From State");
		List<State> states = (List<State>)query.getResultList();
	    return states;
	}

	@Override
	public State getState(Long id) {
		return getCurrentSession().find(State.class, id);
	}
}
