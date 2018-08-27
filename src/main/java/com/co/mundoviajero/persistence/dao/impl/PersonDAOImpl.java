package com.co.mundoviajero.persistence.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.persistence.entity.Person;

@Repository(value = "PersonDAOImpl")
@Transactional
public class PersonDAOImpl extends BaseDAO implements IPersonDAO{

	@Override
	public List<Person> getPeople() {
		Query query = getCurrentSession().createQuery("From Person");
	    return (List<Person>)query.getResultList();
	}

	@Override
	public Person createPerson(Person person) {
		Person newPerson = new Person();
		newPerson.setIdentification(person.getIdentification());
		newPerson.setRNT(person.getRNT());
		newPerson.setName(person.getName());
		newPerson.setLastName(person.getLastName());
		newPerson.setBirthday(person.getBirthday());
		newPerson.setEmail(person.getEmail());
		newPerson.setPhoneNumber(person.getPhoneNumber());
		newPerson.setAddress(person.getAddress());
		newPerson.setPassword(person.getPassword());
		newPerson.setCalification(person.getCalification());
		newPerson.setProfilePhoto(person.getProfilePhoto());
		newPerson.setToken(person.getToken());
		newPerson.setProfileId(person.getProfileId());
		newPerson.setStateId(person.getStateId());		
		return newPerson;
	}

}
