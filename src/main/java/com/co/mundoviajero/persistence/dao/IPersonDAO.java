package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.dto.person.CreatePersonDTO;
import com.co.mundoviajero.dto.person.PersonDTO;
import com.co.mundoviajero.util.exception.ValidationException;

public interface IPersonDAO {
	
	List<PersonDTO> getAllPeople();
	PersonDTO getPerson(Long searchParameter);
	PersonDTO getPersonWithParameters(Map<String, String> parameters);
	boolean createPerson(CreatePersonDTO person) throws ValidationException;
	boolean existPersonTourist(String email);
	boolean existPersonGuide(String identification,String rnt,String email);
	boolean updatePerson(Map<String, String> parameters, String identifier) throws ValidationException;
	PersonDTO login(Map<String, String> loginParameters);
	
}
