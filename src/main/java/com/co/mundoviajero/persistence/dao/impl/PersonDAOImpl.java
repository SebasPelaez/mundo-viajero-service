package com.co.mundoviajero.persistence.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.util.exception.ValidationException;

@Repository(value = "PersonDAOImpl")
@Transactional
public class PersonDAOImpl extends BaseDAO implements IPersonDAO {

	@Override
	public List<PersonDTO> getAllPeople() {
		Query query = getCurrentSession().createQuery("From Person");
		if (query.getResultList().isEmpty())
			return null;
		List<Person> people = (List<Person>) query.getResultList();
		List<PersonDTO> peopleDTO = new ArrayList<>();
		for (Person p : people) {
			peopleDTO.add(setPersonDTO(p));
		}
		return peopleDTO;
	}

	@Override
	public PersonDTO createPerson(PersonDTO person) throws ValidationException {

		Person newPerson = setPerson(person);
		Double calification = 0.0;

		try {
			newPerson.setCalification(calification);
			getCurrentSession().saveOrUpdate(newPerson);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return person;
	}

	@Override
	public boolean existPersonGuide(String identification, String rnt, String email) {
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
	public PersonDTO getPerson(Long id) {
		
		PersonDTO personDTO = null;
		String queryString = "select p from Person p where p.id = :id";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("id", id);

		if (query.getResultList().isEmpty())
			return null;
		
		personDTO = setPersonDTO((Person) query.getSingleResult());

		return personDTO;
	}

	@Override
	public PersonDTO updatePerson(PersonDTO person) throws ValidationException {

		Person personToModify = setPerson(person);
		try {
			Query query = getCurrentSession()
					.createQuery("update Person p set p.email = :email, p.phoneNumber = :phoneNumber, p.address = :address,"
							+ " p.password = :password, p.calification = :calification, p.token = :token, p.profilePhoto = :profilePhoto,"
							+ " p.profileId = :profileId, p.stateId = :stateId where p.id = :id");
			query.setParameter("id", personToModify.getId());
			query.setParameter("email", personToModify.getEmail());
			query.setParameter("phoneNumber", personToModify.getPhoneNumber());
			query.setParameter("address", personToModify.getAddress());
			query.setParameter("password", personToModify.getPassword());
			query.setParameter("calification", personToModify.getCalification());
			query.setParameter("token", personToModify.getToken());
			query.setParameter("profilePhoto", personToModify.getProfilePhoto());
			query.setParameter("profileId", personToModify.getProfileId());
			query.setParameter("stateId", personToModify.getStateId());
			query.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return person;

	}

	@Override
	public PersonDTO getPersonWithParameters(Map<String, String> parameters) {
		PersonDTO personDTO = null;
		String queryString = "";
		
		String key = (String) parameters.keySet().toArray()[0];
		String value = "";	
		
		switch (key) {
			case "identification":
				queryString = "select p from Person p where upper(p.identification) = upper(:search)";
				value = parameters.get("identification");
				break;
			case "rnt":
				queryString = "select p from Person p where upper(p.rnt) = upper(:search)";
				value = parameters.get("rnt");
				break;
			case "email":
				queryString = "select p from Person p where upper(p.email) = upper(:search)";
				value = parameters.get("email");
				break;
			default:
				break;
		}
		
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("search", value);
		
		if (query.getResultList().isEmpty())
			return null;
		
		personDTO = setPersonDTO((Person) query.getSingleResult());

		return personDTO;
	}
	
	private Person setPerson(PersonDTO personDTO) {
		Person person = new Person();

		try {
			person.setId(personDTO.getId());
			person.setIdentification(personDTO.getIdentification());
			person.setRNT(personDTO.getRnt());
			person.setName(personDTO.getName());
			person.setLastName(personDTO.getLastName());

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = format.parse(personDTO.getBirthday());
			java.sql.Date sql = new java.sql.Date(parsed.getTime());
			person.setBirthday(sql);

			person.setEmail(personDTO.getEmail());
			person.setPhoneNumber(personDTO.getPhoneNumber());
			person.setAddress(personDTO.getAddress());
			person.setPassword(personDTO.getPassword());
			person.setCalification(personDTO.getCalification());
			person.setProfilePhoto(personDTO.getProfilePhoto());
			person.setToken(personDTO.getToken());
			person.setProfileId(personDTO.getProfileId());
			person.setStateId(personDTO.getStateId());

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return person;
	}

	private PersonDTO setPersonDTO(Person person) {
		PersonDTO personDTO = new PersonDTO();

		try {
			personDTO.setId(person.getId());
			personDTO.setIdentification(person.getIdentification().trim());
			personDTO.setRnt(person.getRNT().trim());
			personDTO.setName(person.getName().trim());
			personDTO.setLastName(person.getLastName().trim());
			personDTO.setBirthday(person.getBirthday().toString().trim());
			personDTO.setEmail(person.getEmail().trim());
			personDTO.setPhoneNumber(person.getPhoneNumber().trim());
			personDTO.setAddress(person.getAddress().trim());
			personDTO.setPassword(person.getPassword().trim());
			personDTO.setCalification(person.getCalification());
			personDTO.setProfilePhoto(person.getProfilePhoto().trim());
			personDTO.setToken(person.getToken().trim());
			personDTO.setProfileId(person.getProfileId());
			personDTO.setStateId(person.getStateId());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return personDTO;
	}

}
