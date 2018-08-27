package com.co.mundoviajero.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.StateDTO;
import com.co.mundoviajero.persistence.dao.IStateDAO;

@Service
public class StateBusiness {

	@Autowired
    private IStateDAO stateDAO; 
		
	public ResponseEntity<ResponseDTO> getAllStates(){
		return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",stateDAO.getAllStates()),HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseDTO> createState(StateDTO state) {
		return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",stateDAO.createState(state)),HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseDTO> getState(Long id) {
		return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",stateDAO.getState(id)),HttpStatus.OK);
	}
	

}
