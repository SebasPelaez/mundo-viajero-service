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
		throw new ValidationException("No existen registros de personas");		
	}
	
	public ResponseEntity<ResponseDTO> getPerson(String searchParameter) throws ValidationException{
		Long id = 0L;
		if(StringUtils.isNumeric(searchParameter)) id = Long.parseLong(searchParameter);
		
		Person person = personDAO.getPerson(id, searchParameter, searchParameter, searchParameter);
		if(person != null) {
			return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",
					person),HttpStatus.OK);
		}
		throw new ValidationException("No existen registros asocidos de esa persona");
		
	}
	
	public ResponseEntity<ResponseDTO> createPerson(PersonDTO person) throws ValidationException {
		StringBuilder sb = new StringBuilder();

        sb.append(Validator.valideString(person.getIdentification(), FieldConstants.PERSON_IDENTIFICATION,
        		FieldConstants.PERSON_IDENTIFICATION_LENGTH, FieldConstants.PERSON_IDENTIFICATION_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_RNT,
        		FieldConstants.PERSON_RNT_LENGTH, FieldConstants.PERSON_RNT_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getIdentification(), FieldConstants.PERSON_NAME,
        		FieldConstants.PERSON_NAME_LENGTH, FieldConstants.PERSON_NAME_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_LASTNAME,
        		FieldConstants.PERSON_LASTNAME_LENGTH, FieldConstants.PERSON_LASTNAME_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getIdentification(), FieldConstants.PERSON_BIRTHDAY,
        		FieldConstants.PERSON_BIRTHDAY_LENGTH, FieldConstants.PERSON_BIRTHDAY_OBLIGATORY));
        
        sb.append(Validator.valideEmail(FieldConstants.PERSON_EMAIL, person.getEmail()));
        
        sb.append(Validator.valideString(person.getIdentification(), FieldConstants.PERSON_PHONENUMBER,
        		FieldConstants.PERSON_PHONENUMBER_LENGTH, FieldConstants.PERSON_PHONENUMBER_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_ADDRESS,
        		FieldConstants.PERSON_ADDRESS_LENGTH, FieldConstants.PERSON_ADDRESS_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getIdentification(), FieldConstants.PERSON_PASSWORD,
        		FieldConstants.PERSON_PASSWORD_LENGTH, FieldConstants.PERSON_PASSWORD_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_CALIFICATION,
        		FieldConstants.PERSON_CALIFICATION_LENGTH, FieldConstants.PERSON_CALIFICATION_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_TOKEN,
        		FieldConstants.PERSON_TOKEN_LENGTH, FieldConstants.PERSON_TOKEN_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getIdentification(), FieldConstants.PERSON_PROFILEPHOTO,
        		FieldConstants.PERSON_PROFILEPHOTO_LENGTH, FieldConstants.PERSON_PROFILEPHOTO_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_PROFILEID,
        		FieldConstants.PERSON_PROFILEID_LENGTH, FieldConstants.PERSON_PROFILEID_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_STATEID,
        		FieldConstants.PERSON_STATEID_LENGTH, FieldConstants.PERSON_STATEID_OBLIGATORY));       
        

        /**
         * FALTA LA VALIDACIÒN DEL TIPO DE CAMPO
         */
        
        if (sb.toString().length() > 0) {
        	throw new ValidationException(sb.toString());
        }
        if(person.getIdentification() == null)person.setIdentification("");
        if(person.getRnt() == null)person.setRnt("");
        if( !personDAO.existPerson(person.getIdentification(),person.getRnt(),person.getEmail())){
			
			return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",personDAO.createPerson(person)),
					HttpStatus.OK);
		}else {
            throw new ValidationException("El usuario ya existe");
        }
		
	}
		
}
