package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.persistence.entity.Person;

public interface IPersonDAO {
	
	List<Person> getPeople();
	Person createPerson(Person person);
}
