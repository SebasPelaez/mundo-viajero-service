package com.co.mundoviajero.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.persistence.dao.IStateDAO;
import com.co.mundoviajero.persistence.entity.State;

@Repository(value = "StateDAOImpl")
@Transactional
public class StateDAOImpl extends BaseDAO implements IStateDAO{

	@Override
	public List<State> getStates() {		
		Query query = getCurrentSession().createQuery("From State");
	    return (List<State>)query.getResultList();
	}

	@Override
	public State createState(State state) {
		State newState = new State();
		newState.setDescription(state.getDescription());
		newState.setBelongsTo(state.getBelongsTo());
		
		getCurrentSession().saveOrUpdate(newState);
		return newState;
	}

}
