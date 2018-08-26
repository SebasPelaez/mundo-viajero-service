package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.persistence.entity.State;


public interface IStateDAO {
	
	List<State> getStates();
	State createState(State state);
}
