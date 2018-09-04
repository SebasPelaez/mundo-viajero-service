package com.co.mundoviajero.persistence.dao.impl;

import java.util.ArrayList;
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
	public List<StateDTO> getAllStates() {
		List<StateDTO> statesDTO = new ArrayList<>();
		Query query = getCurrentSession().createQuery("From State");
		List<State> states = (List<State>)query.getResultList();
		
		for(State s: states) {
			statesDTO.add(setStateDTO(s));
		}
		
	    return statesDTO;
	}

	@Override
	public StateDTO getState(Long id) {
		Query query = getCurrentSession().createQuery("select s from State s where s.id = :id");
		query.setParameter("id", id);
		StateDTO stateDTO = setStateDTO((State) query.getSingleResult());
		return stateDTO;
	}
	
	private StateDTO setStateDTO(State state) {
		
		StateDTO stateDTO = new StateDTO();
		stateDTO.setId(state.getId());
		stateDTO.setDescription(state.getDescription().trim());
		stateDTO.setBelongsTo(state.getBelongsTo().trim());
		
		return stateDTO;
	}

}
