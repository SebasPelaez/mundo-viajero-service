package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.dto.StateDTO;

public interface IStateDAO {	

	List<StateDTO> getAllStates();
	StateDTO getState(Long id);

}
