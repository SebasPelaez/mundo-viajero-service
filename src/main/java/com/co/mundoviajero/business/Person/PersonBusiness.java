package com.co.mundoviajero.business.Person;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;

@Service
public class PersonBusiness {
	
	@Autowired
    private IPersonDAO personDAO;
	
	public ResponseEntity<ResponseDTO> getAllPeople()  throws Exception{
		List<Person> people = personDAO.getAllPeople();
		if (people != null) {
			return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",people),HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","No encontro registros"),HttpStatus.NO_CONTENT);
	}
	
	public ResponseEntity<ResponseDTO> getPerson(String searchParameter) throws ValidationException{
		Person person = null;
		
		if(StringUtils.isNumeric(searchParameter)) {
			person = personDAO.getPerson(Long.parseLong(searchParameter));
		}else {
			person = personDAO.getPerson(searchParameter);
		}
		
		if(person != null) {
			return new ResponseEntity<>(new ResponseDTO("Codigo","DESC_SUCCESS","Mensaje",
					person),HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","No encontro registros"),HttpStatus.NO_CONTENT);
		
		
	}
	
	public ResponseEntity<ResponseDTO> createPerson(PersonDTO person) throws ValidationException {
		StringBuilder sb = new StringBuilder();

        sb.append(Validator.valideString(person.getIdentification(), FieldConstants.PERSON_IDENTIFICATION,
        		FieldConstants.PERSON_IDENTIFICATION_LENGTH, FieldConstants.PERSON_IDENTIFICATION_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_RNT,
        		FieldConstants.PERSON_RNT_LENGTH, FieldConstants.PERSON_RNT_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getName(), FieldConstants.PERSON_NAME,
        		FieldConstants.PERSON_NAME_LENGTH, FieldConstants.PERSON_NAME_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getLastName(), FieldConstants.PERSON_LASTNAME,
        		FieldConstants.PERSON_LASTNAME_LENGTH, FieldConstants.PERSON_LASTNAME_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getBirthday(), FieldConstants.PERSON_BIRTHDAY,
        		FieldConstants.PERSON_BIRTHDAY_LENGTH, FieldConstants.PERSON_BIRTHDAY_OBLIGATORY));
        
        sb.append(Validator.valideEmail(FieldConstants.PERSON_EMAIL, person.getEmail()));
        
        sb.append(Validator.valideString(person.getPhoneNumber(), FieldConstants.PERSON_PHONENUMBER,
        		FieldConstants.PERSON_PHONENUMBER_LENGTH, FieldConstants.PERSON_PHONENUMBER_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getAddress(), FieldConstants.PERSON_ADDRESS,
        		FieldConstants.PERSON_ADDRESS_LENGTH, FieldConstants.PERSON_ADDRESS_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getPassword(), FieldConstants.PERSON_PASSWORD,
        		FieldConstants.PERSON_PASSWORD_LENGTH, FieldConstants.PERSON_PASSWORD_OBLIGATORY));
        
        sb.append(Validator.validateNumber((""+person.getCalification()), FieldConstants.PERSON_CALIFICATION,
        		FieldConstants.PERSON_CALIFICATION_LENGTH, FieldConstants.PERSON_CALIFICATION_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getToken(), FieldConstants.PERSON_TOKEN,
        		FieldConstants.PERSON_TOKEN_LENGTH, FieldConstants.PERSON_TOKEN_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getProfilePhoto(), FieldConstants.PERSON_PROFILEPHOTO,
        		FieldConstants.PERSON_PROFILEPHOTO_LENGTH, FieldConstants.PERSON_PROFILEPHOTO_OBLIGATORY));
        
        sb.append(Validator.validateNumber((""+person.getProfileId()), FieldConstants.PERSON_PROFILEID,
        		FieldConstants.PERSON_PROFILEID_LENGTH, FieldConstants.PERSON_PROFILEID_OBLIGATORY));
        
        sb.append(Validator.validateNumber((""+person.getStateId()), FieldConstants.PERSON_STATEID,
        		FieldConstants.PERSON_STATEID_LENGTH, FieldConstants.PERSON_STATEID_OBLIGATORY));              
        
        if (sb.toString().length() > 0) {
        	throw new ValidationException(sb.toString());
        }
        //Verify if already exist a Tourist
        if(person.getProfileId() == 1) {
        	person.setIdentification("");
        	person.setRnt("");
        	if(!personDAO.existPersonTourist(person.getEmail())) {
        		return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",personDAO.createPerson(person)),
    					HttpStatus.OK);
        	}else {
                throw new ValidationException("El usuario ya existe");
            }
        }
        //At this part the new Person should be a Guide
        if( !personDAO.existPersonGuide(person.getIdentification(),person.getRnt(),person.getEmail())){			
        	return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",personDAO.createPerson(person)),
					HttpStatus.OK);        	
		}else {
            throw new ValidationException("El usuario ya existe");
        }
		
	}
	
	public ResponseEntity<ResponseDTO> updatePerson(PersonDTO person ,String searchParameter) throws ValidationException{
		Person personToModify = null;
		
		if(StringUtils.isNumeric(searchParameter)) {
			personToModify = personDAO.getPerson(Long.parseLong(searchParameter));
		}else {
			personToModify = personDAO.getPerson(searchParameter);
		}
		
		if(personToModify != null) {
			StringBuilder sb = new StringBuilder();

	        sb.append(Validator.valideString(person.getIdentification(), FieldConstants.PERSON_IDENTIFICATION,
	        		FieldConstants.PERSON_IDENTIFICATION_LENGTH, FieldConstants.PERSON_IDENTIFICATION_OBLIGATORY));
	        
	        sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_RNT,
	        		FieldConstants.PERSON_RNT_LENGTH, FieldConstants.PERSON_RNT_OBLIGATORY));
	        
	        sb.append(Validator.valideString(person.getName(), FieldConstants.PERSON_NAME,
	        		FieldConstants.PERSON_NAME_LENGTH, FieldConstants.PERSON_NAME_OBLIGATORY));
	        
	        sb.append(Validator.valideString(person.getLastName(), FieldConstants.PERSON_LASTNAME,
	        		FieldConstants.PERSON_LASTNAME_LENGTH, FieldConstants.PERSON_LASTNAME_OBLIGATORY));
	        
	        sb.append(Validator.valideString(person.getBirthday(), FieldConstants.PERSON_BIRTHDAY,
	        		FieldConstants.PERSON_BIRTHDAY_LENGTH, FieldConstants.PERSON_BIRTHDAY_OBLIGATORY));
	        
	        sb.append(Validator.valideEmail(FieldConstants.PERSON_EMAIL, person.getEmail()));
	        
	        sb.append(Validator.valideString(person.getPhoneNumber(), FieldConstants.PERSON_PHONENUMBER,
	        		FieldConstants.PERSON_PHONENUMBER_LENGTH, FieldConstants.PERSON_PHONENUMBER_OBLIGATORY));
	        
	        sb.append(Validator.valideString(person.getAddress(), FieldConstants.PERSON_ADDRESS,
	        		FieldConstants.PERSON_ADDRESS_LENGTH, FieldConstants.PERSON_ADDRESS_OBLIGATORY));
	        
	        sb.append(Validator.valideString(person.getPassword(), FieldConstants.PERSON_PASSWORD,
	        		FieldConstants.PERSON_PASSWORD_LENGTH, FieldConstants.PERSON_PASSWORD_OBLIGATORY));
	        
	        sb.append(Validator.validateNumber((""+person.getCalification()), FieldConstants.PERSON_CALIFICATION,
	        		FieldConstants.PERSON_CALIFICATION_LENGTH, FieldConstants.PERSON_CALIFICATION_OBLIGATORY));
	        
	        sb.append(Validator.valideString(person.getToken(), FieldConstants.PERSON_TOKEN,
	        		FieldConstants.PERSON_TOKEN_LENGTH, FieldConstants.PERSON_TOKEN_OBLIGATORY));
	        
	        sb.append(Validator.valideString(person.getProfilePhoto(), FieldConstants.PERSON_PROFILEPHOTO,
	        		FieldConstants.PERSON_PROFILEPHOTO_LENGTH, FieldConstants.PERSON_PROFILEPHOTO_OBLIGATORY));
	        
	        sb.append(Validator.validateNumber((""+person.getProfileId()), FieldConstants.PERSON_PROFILEID,
	        		FieldConstants.PERSON_PROFILEID_LENGTH, FieldConstants.PERSON_PROFILEID_OBLIGATORY));
	        
	        sb.append(Validator.validateNumber((""+person.getStateId()), FieldConstants.PERSON_STATEID,
	        		FieldConstants.PERSON_STATEID_LENGTH, FieldConstants.PERSON_STATEID_OBLIGATORY));              
	        
	        if (sb.toString().length() > 0) {
	        	throw new ValidationException(sb.toString());
	        }
	        //Verify if already exist a Tourist
	        /*
	        if(person.getProfileId() == 1) {
	        	person.setIdentification("");
	        	person.setRnt("");
	        	if(!personDAO.existPersonTourist(person.getEmail())) {
	        		return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",personDAO.createPerson(person)),
	    					HttpStatus.OK);
	        	}else {
	                throw new ValidationException("El usuario ya existe");
	            }
	        }
	        //At this part the new Person should be a Guide
	        if( !personDAO.existPersonGuide(person.getIdentification(),person.getRnt(),person.getEmail())){			
	        	return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",personDAO.createPerson(person)),
						HttpStatus.OK);        	
			}else {
	            throw new ValidationException("El usuario ya existe");
	        }
	        */	        
	        return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",personDAO.updatePerson(person, personToModify)),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","No encontro registros"),HttpStatus.NO_CONTENT);
		
		
	}
		
}
