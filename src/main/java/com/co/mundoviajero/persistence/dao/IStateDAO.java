package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.dto.StateDTO;
import com.co.mundoviajero.persistence.entity.State;


public interface IStateDAO {
	
	List<State> getAllStates();
	StateDTO createState(StateDTO state);
	State getState(Long id);

}
