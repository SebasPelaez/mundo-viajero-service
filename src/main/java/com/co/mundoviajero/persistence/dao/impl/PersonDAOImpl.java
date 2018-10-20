package com.co.mundoviajero.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.util.exception.ValidationException;

@Repository(value = "PersonDAOImpl")
@Transactional
public class PersonDAOImpl extends BaseDAO implements IPersonDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAllPeople() {
		
		List<Person> people = new ArrayList<>();
		Query query = getCurrentSession().createQuery("From Person");
		if (query.getResultList().isEmpty())
			return people;
		people = (List<Person>) query.getResultList();
		return people;
	}

	@Override
	public boolean createPerson(Person person) throws ValidationException {

		try {
			getCurrentSession().saveOrUpdate(person);
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
	public Person getPerson(Long id) {		
		return getCurrentSession().find(Person.class, id);
	}

	@Override
	public Person getPersonWithParameters(Map<String, String> parameters) {
	
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
		
		return (Person) query.getSingleResult();
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
	public Person login(String email, String password) {
		Person person = null;
		
		String queryString = "select p from Person p where upper(p.email) = upper(:email) and p.password = :password";
		
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		if (query.getResultList().isEmpty()) {
			return person;
		}

		return (Person) query.getSingleResult();
	}
	
}
