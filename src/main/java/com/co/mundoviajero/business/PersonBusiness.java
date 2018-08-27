package com.co.mundoviajero.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.persistence.entity.Person;

@Service
public class PersonBusiness {
	
	@Autowired
	private IPersonDAO personDAO;
	
	public List<Person> getPeople(){
		return personDAO.getPeople();
	}
	
	public Person createPerson(Person person) {
		return personDAO.createPerson(person);
	}
}
