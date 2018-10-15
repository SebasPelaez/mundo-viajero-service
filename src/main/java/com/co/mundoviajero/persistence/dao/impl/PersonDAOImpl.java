package com.co.mundoviajero.persistence.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.dto.person.CreatePersonDTO;
import com.co.mundoviajero.dto.person.PersonDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.persistence.entity.Profile;
import com.co.mundoviajero.persistence.entity.State;
import com.co.mundoviajero.util.exception.ValidationException;

@Repository(value = "PersonDAOImpl")
@Transactional
public class PersonDAOImpl extends BaseDAO implements IPersonDAO {

	@SuppressWarnings("unchecked")
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
	public boolean createPerson(CreatePersonDTO person) throws ValidationException {

		Person newPerson = setPerson(person);

		try {
			getCurrentSession().saveOrUpdate(newPerson);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
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
	
	@Override
	public boolean updatePerson(Map<String, String> parameters, String identifier) throws ValidationException {
		StringBuffer parametersQueryString = new StringBuffer();
		String baseQueryString = "update Person p set ";
		String conditionQueryString = " where p."+identifier+" = :"+identifier;
		
		try {
			
			for(String parameter: parameters.keySet()) {
				if(!parameter.equals(identifier)) {
					parametersQueryString.append("p."+parameter+" = '"+parameters.get(parameter)+"', ");
				}
				
			}
			
			parametersQueryString.replace(parametersQueryString.length()-2, parametersQueryString.length(), "");
			String fullQueryString = baseQueryString + parametersQueryString.toString() + conditionQueryString;
			
			Query query = getCurrentSession().createQuery(fullQueryString);
			query.setParameter(identifier,parameters.get(identifier));
			query.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;		
	}
	
	@Override
	public PersonDTO login(Map<String, String> loginParameters) {
		PersonDTO personDTO = null;
		
		String queryString = "";
		String email = loginParameters.get("email");
		String password = loginParameters.get("password");
		queryString = "select p from Person p where p.email = :email and p.password = :password";					
		
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		if (query.getResultList().isEmpty()) {
			return personDTO;
		}
		
		personDTO = setPersonDTO((Person) query.getSingleResult());		
		return personDTO;
	}
	
	private Person setPerson(CreatePersonDTO personDTO) {
		Person person = new Person();

		try {
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
			
			Profile profile = new Profile();
			profile.setId(Long.parseLong(personDTO.getProfileId()));
			person.setProfile(profile);
			
			State state = new State();
			state.setId(Long.parseLong(personDTO.getStateId()));
			person.setStateId(state);

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
			personDTO.setProfile(person.getProfile());
			personDTO.setState(person.getStateId());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return personDTO;
	}
	
}
