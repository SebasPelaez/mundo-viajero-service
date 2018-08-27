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
		/*
		List<StateDTO> stateDTOList = new ArrayList<>();
		for (State state: states) {
			StateDTO stateDTO = new StateDTO();
			stateDTO.setDescription(state.getDescription());
			stateDTO.setBelongsTo(stateDTO.getBelongsTo());
			stateDTOList.add(stateDTO);
		}*/
	    return (List<State>)query.getResultList();
	}

	@Override
	public StateDTO createState(StateDTO state) {
		State newState = new State();
		newState.setDescription(state.getDescription());
		newState.setBelongsTo(state.getBelongsTo());
		
		getCurrentSession().saveOrUpdate(newState);
		return state;
	}

	@Override
	public State getState(Long id) {
		Query query = getCurrentSession().createQuery("select s from State s where s.id = :id");
		query.setParameter("id", id);
		return (State) query.getSingleResult();
	}

}
