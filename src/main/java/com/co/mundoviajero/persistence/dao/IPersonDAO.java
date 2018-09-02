package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.util.exception.ValidationException;

public interface IPersonDAO {
	
	List<PersonDTO> getAllPeople();
	PersonDTO createPerson(PersonDTO person) throws ValidationException;
	boolean existPersonTourist(String email);
	boolean existPersonGuide(String identification,String rnt,String email);
	PersonDTO getPerson(Object searchParameter);	
	PersonDTO updatePerson(PersonDTO person) throws ValidationException;
}
