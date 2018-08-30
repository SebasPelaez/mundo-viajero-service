package com.co.mundoviajero.persistence.dao;

import java.util.List;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.persistence.entity.Person;

public interface IPersonDAO {
	
	List<Person> getAllPeople();
	PersonDTO createPerson(PersonDTO person);
	boolean existPerson(String identification,String rnt,String email);
	Person getPerson(Long id,String identification,String rnt,String email);
}
