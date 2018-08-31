package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.util.exception.ValidationException;

public interface IPersonDAO {
	
	List<Person> getAllPeople();
	PersonDTO createPerson(PersonDTO person) throws ValidationException;
	boolean existPersonTourist(String email);
	boolean existPersonGuide(String identification,String rnt,String email);
	Person getPerson(Object searchParameter);
}
