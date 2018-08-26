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

	@Override
	public List<State> getStates() {
		Query query = getCurrentSession().createQuery("select * from State");
	    return (List<State>)query.getResultList();
	}

	@Override
	public String prueba() {
		// TODO Auto-generated method stub
		return "Hellow World! PI";
	}

}
