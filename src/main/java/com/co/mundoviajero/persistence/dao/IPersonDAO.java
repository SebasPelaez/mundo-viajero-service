package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.util.exception.ValidationException;

public interface IPersonDAO {
	
	List<Person> getAllPeople();
	Person getPerson(Long searchParameter);
	Person getPersonWithParameters(Map<String, String> parameters);
	boolean createPerson(Person person) throws ValidationException;
	boolean existPersonTourist(String email);
	boolean existPersonGuide(String identification,String rnt,String email);
	boolean updatePerson(Map<String, String> parameters, String identifier) throws ValidationException;
	Person login(String email, String password);
	
}
