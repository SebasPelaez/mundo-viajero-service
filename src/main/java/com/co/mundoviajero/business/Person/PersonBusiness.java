package com.co.mundoviajero.business.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;

@Service
public class PersonBusiness {
	
	@Autowired
    private IPersonDAO personDAO;
	
	public ResponseEntity<ResponseDTO> getAllPersons(){
		return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",personDAO.getAllPeople()),HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseDTO> createPerson(PersonDTO person) {
		StringBuilder sb = new StringBuilder();

        sb.append(Validator.valideString(person.getIdentification(), FieldConstants.PERSON_IDENTIFICATION,
        		FieldConstants.PERSON_IDENTIFICATION_LENGTH, FieldConstants.PERSON_IDENTIFICATION_OBLIGATORY));
        
        sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_RNT,
        		FieldConstants.PERSON_RNT_LENGTH, FieldConstants.PERSON_RNT_OBLIGATORY));

        /**
         * COMPLETAR CON LOS DEMAS
         */
        
        if (sb.toString().length() > 0) {
            return null;
        }
        if(person.getIdentification() == null)person.setIdentification("");
        if(person.getRnt() == null)person.setRnt("");
        if( !personDAO.existPerson(person.getIdentification(),person.getRnt(),person.getEmail())){
			
			return new ResponseEntity<>(new ResponseDTO("SUCCES","DESC_SUCCESS","DESC_SUCCESS",personDAO.createPerson(person)),
					HttpStatus.OK);
		}
		return null;
		
	}
		
}
