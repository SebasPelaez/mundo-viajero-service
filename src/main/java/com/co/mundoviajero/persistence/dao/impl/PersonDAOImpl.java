package com.co.mundoviajero.persistence.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.util.exception.ValidationException;

@Repository(value = "PersonDAOImpl")
@Transactional
public class PersonDAOImpl extends BaseDAO implements IPersonDAO{

	@Override
	public List<Person> getAllPeople() {
		Query query = getCurrentSession().createQuery("From Person");
		if(query.getResultList().isEmpty()) return null;
	    return (List<Person>) query.getResultList();
	}

	@Override
	public PersonDTO createPerson(PersonDTO person) throws ValidationException {
		
		Person newPerson = new Person();
		Double calification = 0.0;
		
		try {
			
			newPerson.setIdentification(person.getIdentification());
			newPerson.setRNT(person.getRnt());
			newPerson.setName(person.getName());
			newPerson.setLastName(person.getLastName());
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	        Date parsed = format.parse(person.getBirthday());
	        java.sql.Date sql = new java.sql.Date(parsed.getTime());
			newPerson.setBirthday(sql);
			
			newPerson.setEmail(person.getEmail());
			newPerson.setPhoneNumber(person.getPhoneNumber());
			newPerson.setAddress(person.getAddress());
			newPerson.setPassword(person.getPassword());
			newPerson.setCalification(calification);
			newPerson.setProfilePhoto(person.getProfilePhoto());
			newPerson.setToken(person.getToken());
			newPerson.setProfileId(person.getProfileId());
			newPerson.setStateId(person.getStateId());
			
			getCurrentSession().saveOrUpdate(newPerson);
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}		
		return person;
	}

	@Override
	public boolean existPersonGuide(String identification,String rnt,String email) {
		String queryString = "select p from Person p where upper(p.identification) = upper(:identification) or"
				+ " upper(p.rnt) = upper(:rnt) or upper(p.email) = upper(:email)";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("identification", identification);
		query.setParameter("rnt", rnt);
		query.setParameter("email", email);
		return !query.getResultList().isEmpty();
	}
	
	@Override
	public boolean existPersonTourist(String email) {
		String queryString = "select p from Person p where upper(p.email) = upper(:email)";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("email", email);
		return !query.getResultList().isEmpty();
	}

	@Override
	public Person getPerson(Object object) {
		String queryString = "";
		String searchParameter = "";
		Long id = 0L;
		
		if(object instanceof Long) {
			id = (Long)object;
			searchParameter = String.valueOf(object);
			queryString = "select p from Person p where p.id = :id or p.identification = :searchParameter"
					+ " or p.rnt = :searchParameter";
		}else {
			searchParameter = String.valueOf(object);
			queryString = "select p from Person p where p.id = :id or upper(p.identification) = upper(:searchParameter) or "
					+ " upper(p.rnt) = upper(:searchParameter) or upper(p.email) = upper(:searchParameter)";
		}
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("searchParameter", searchParameter);
		query.setParameter("id", id);
		
		if(query.getResultList().isEmpty()) return null;
		
		return (Person) query.getSingleResult();
	}

	@Override
	public PersonDTO updatePerson(PersonDTO person, Person personToModify) throws ValidationException {		
		try {	
			if(person.getIdentification() != personToModify.getIdentification() && person.getIdentification() != null) {
				personToModify.setIdentification(person.getIdentification());
			}
			if(person.getRnt() != personToModify.getRNT() && person.getRnt() != null) {
				personToModify.setRNT(person.getRnt());
			}
			if(person.getName() != personToModify.getName() && person.getName() != null) {
				personToModify.setName(person.getName());
			}
			if(person.getLastName() != personToModify.getLastName() && person.getLastName() != null) {
				personToModify.setLastName(person.getLastName());
			}			
			
			if(person.getBirthday() != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		        Date parsed = format.parse(person.getBirthday());
		        java.sql.Date sql = new java.sql.Date(parsed.getTime());
				if(sql != personToModify.getBirthday()) {					
			        personToModify.setBirthday(sql);	
				}
			} 
			
			if(person.getEmail() != personToModify.getEmail() && person.getEmail() != null) {
				personToModify.setEmail(person.getEmail());	
			}
			
			if(person.getPhoneNumber() != personToModify.getPhoneNumber() && person.getPhoneNumber() != null) {
				personToModify.setPhoneNumber(person.getPhoneNumber());
			}	        
			if(person.getAddress() != personToModify.getAddress() && person.getAddress() != null) {
				personToModify.setAddress(person.getAddress());
			}
			if(person.getPassword() != personToModify.getPassword() && person.getPassword() != null) {
				personToModify.setPassword(person.getPassword());
			}
			/* Presenta problemas
			if(person.getCalification() != personToModify.getCalification() && person.getCalification() != null) {
				personToModify.setCalification(person.getCalification());
			}
			*/
			if(person.getProfilePhoto() != personToModify.getProfilePhoto() && person.getProfilePhoto() != null) {
				personToModify.setProfilePhoto(person.getProfilePhoto());
			}
			if(person.getToken() != personToModify.getToken() && person.getToken() != null) {
				personToModify.setToken(person.getToken());
			}
			if(person.getProfileId() != personToModify.getProfileId() && person.getProfileId() != null) {
				personToModify.setProfileId(person.getProfileId());
			}
			if(person.getStateId() != personToModify.getStateId() && person.getStateId() != null) {
				personToModify.setStateId(person.getStateId());	
			}
			
			getCurrentSession().saveOrUpdate(personToModify);
			
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}		
		return person;
	}

}
