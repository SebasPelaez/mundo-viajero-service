package com.co.mundoviajero.business.Person;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;

@Service
public class PersonBusiness {

	@Autowired
	private IPersonDAO personDAO;

	@Autowired
	private MessageSourceAccessor messageSource;

	public ResponseEntity<ResponseDTO> getAllPeople() throws Exception {
		List<PersonDTO> people = personDAO.getAllPeople();
		if (people != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), people),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseDTO> getPerson(String searchParameter) throws ValidationException {
		PersonDTO person = null;

		if (StringUtils.isNumeric(searchParameter)) {
			person = personDAO.getPerson(Long.parseLong(searchParameter));
		} else {
			person = personDAO.getPerson(searchParameter);
		}

		if (person != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), person),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<ResponseDTO> createPerson(PersonDTO person) throws ValidationException {
		StringBuilder sb = new StringBuilder();

		sb.append(Validator.valideString(person.getIdentification(), FieldConstants.PERSON_IDENTIFICATION,
				FieldConstants.PERSON_IDENTIFICATION_LENGTH, FieldConstants.PERSON_IDENTIFICATION_OBLIGATORY));

		sb.append(Validator.valideString(person.getRnt(), FieldConstants.PERSON_RNT, FieldConstants.PERSON_RNT_LENGTH,
				FieldConstants.PERSON_RNT_OBLIGATORY));

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

		sb.append(Validator.validateNumber(("" + person.getCalification()), FieldConstants.PERSON_CALIFICATION,
				FieldConstants.PERSON_CALIFICATION_LENGTH, FieldConstants.PERSON_CALIFICATION_OBLIGATORY));

		sb.append(Validator.valideString(person.getToken(), FieldConstants.PERSON_TOKEN,
				FieldConstants.PERSON_TOKEN_LENGTH, FieldConstants.PERSON_TOKEN_OBLIGATORY));

		sb.append(Validator.valideString(person.getProfilePhoto(), FieldConstants.PERSON_PROFILEPHOTO,
				FieldConstants.PERSON_PROFILEPHOTO_LENGTH, FieldConstants.PERSON_PROFILEPHOTO_OBLIGATORY));

		sb.append(Validator.validateNumber(("" + person.getProfileId()), FieldConstants.PERSON_PROFILEID,
				FieldConstants.PERSON_PROFILEID_LENGTH, FieldConstants.PERSON_PROFILEID_OBLIGATORY));

		sb.append(Validator.validateNumber(("" + person.getStateId()), FieldConstants.PERSON_STATEID,
				FieldConstants.PERSON_STATEID_LENGTH, FieldConstants.PERSON_STATEID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(sb.toString());
		}

		if (Validator.validateBirthday(person.getBirthday())) {
			setNullAttributes(person);
			if (person.getProfileId() == 1) {
				
				if (!personDAO.existPersonTourist(person.getEmail())) {
					return new ResponseEntity<>(
							new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
									messageSource.getMessage("DESC_SUCCESS"),
									messageSource.getMessage("POST_DESC_SUCCESS"), personDAO.createPerson(person)),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<>(
							new ResponseDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("DESC_ERR"),
									messageSource.getMessage("EXISTING_TOURIST_DESC_ERROR"), null),
							HttpStatus.PRECONDITION_REQUIRED);
				}
			}
			// At this part the new Person should be a Guide
			if (!personDAO.existPersonGuide(person.getIdentification(), person.getRnt(), person.getEmail())) {
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("POST_DESC_SUCCESS"),
						personDAO.createPerson(person)), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
						messageSource.getMessage("DESC_ERR"), messageSource.getMessage("EXISTING_GUIDE_DESC_ERROR"), null),
						HttpStatus.PRECONDITION_REQUIRED);
			}
		} else {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("DESC_ERR"), messageSource.getMessage("BIRTHDAY_DESC_ERROR"), null),
					HttpStatus.PRECONDITION_REQUIRED);
		}

	}

	public ResponseEntity<ResponseDTO> updatePerson(PersonDTO personToModify) throws ValidationException {

		PersonDTO currentPerson = personDAO.getPerson(personToModify.getId());

		if (currentPerson == null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
					HttpStatus.PRECONDITION_REQUIRED);
		}

		if (Validator.validateUpdatePerson(currentPerson, personToModify)) {
			PersonDTO person = personDAO.updatePerson(personToModify);
			if(person != null) {
				
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("PUT_DESC_SUCCESS"), person),
						HttpStatus.PRECONDITION_REQUIRED);			
			}else {
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
						messageSource.getMessage("DESC_ERR"), messageSource.getMessage("PUT_DESC_ERROR"), null),
						HttpStatus.PRECONDITION_REQUIRED);
			}
			
		}
		
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("UNAUTHORIZED_PARAMETERS"), null),
				HttpStatus.PRECONDITION_REQUIRED);
	}
	
	private void setNullAttributes(PersonDTO person) {
		if (person.getIdentification() == null)person.setIdentification("");
		if (person.getRnt() == null)person.setRnt("");
		if (person.getAddress() == null)person.setAddress("");
		if (person.getPassword() == null)person.setPassword("");
		if (person.getCalification() == null)person.setCalification(0.0);
		if (person.getToken() == null)person.setToken("");
		if (person.getProfilePhoto() == null)person.setProfilePhoto("");
	}
	
}
