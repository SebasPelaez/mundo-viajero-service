package com.co.mundoviajero.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.persistence.dao.IStateDAO;
import com.co.mundoviajero.persistence.entity.State;

@Service
public class StateBusiness {

	@Autowired
    private IStateDAO stateDAO; 
	
	public List<State> getStates(){
		return stateDAO.getStates();
	}
	

}
