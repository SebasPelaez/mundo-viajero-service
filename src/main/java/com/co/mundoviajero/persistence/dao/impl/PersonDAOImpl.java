package com.co.mundoviajero.persistence.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.persistence.entity.Person;

@Repository(value = "PersonDAOImpl")
@Transactional
public class PersonDAOImpl extends BaseDAO implements IPersonDAO{

	@Override
	public List<Person> getAllPeople() {
		Query query = getCurrentSession().createQuery("From Person");
	    return (List<Person>)query.getResultList();
	}

	@Override
	public PersonDTO createPerson(PersonDTO person) {
		
		Person newPerson = new Person();
		Double calification = 0.0;
		
		newPerson.setIdentification(person.getIdentification());
		newPerson.setRNT(person.getRnt());
		newPerson.setName(person.getName());
		newPerson.setLastName(person.getLastName());
		newPerson.setBirthday(person.getBirthday());
		newPerson.setEmail(person.getEmail());
		newPerson.setPhoneNumber(person.getPhoneNumber());
		newPerson.setAddress(person.getAddress());
		newPerson.setPassword(person.getPassword());
		newPerson.setCalification(calification);
		newPerson.setProfilePhoto(person.getProfilePhoto());
		newPerson.setToken(person.getToken());
		newPerson.setProfileId(person.getProfileId());
		newPerson.setStateId(person.getStateId());
		
		try {
			getCurrentSession().saveOrUpdate(newPerson);
		}catch (Exception e) {
			System.out.println("Error al agregar: " + e);
			return null;
		}		
		return person;
	}

}
