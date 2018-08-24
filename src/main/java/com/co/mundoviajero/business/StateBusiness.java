package com.co.mundoviajero.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.mundoviajero.persistence.dao.IStateDAO;
import com.co.mundoviajero.persistence.entity.State;

public class StateBusiness {

	@Autowired
    private IStateDAO stateDAO; 
	
	public List<State> getStates(){
		return stateDAO.getStates();
	}
	

}
