package com.co.mundoviajero.business.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.business.SetEntitiesIntoDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.person.CreatePersonDTO;
import com.co.mundoviajero.dto.person.PersonResponseDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.persistence.entity.Person;
import com.co.mundoviajero.persistence.entity.Profile;
import com.co.mundoviajero.persistence.entity.State;
import com.co.mundoviajero.util.Constants;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;
import com.co.mundoviajero.util.exception.dto.ErrorDTO;

@Service
public class PersonBusiness {

	@Autowired
	private IPersonDAO personDAO;

	@Autowired
	private MessageSourceAccessor messageSource;

	public ResponseEntity<ResponseDTO> getAllPeople() throws Exception {

		List<Person> people = personDAO.getAllPeople();
		if (CollectionUtils.isNotEmpty(people)) {

			List<PersonResponseDTO> peopleResponse = new ArrayList<>();
			people.forEach(person -> peopleResponse.add(SetEntitiesIntoDTO.setPersonResponseDTO(person)));
			return new ResponseEntity<>(
					new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"), messageSource.getMessage("DESC_SUCCESS"),
							messageSource.getMessage("GET_DESC_SUCCESS"), peopleResponse),
					HttpStatus.OK);
		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("GET_DESC_ERROR_PERSON")));
	}

	public ResponseEntity<ResponseDTO> getPerson(Long id) throws ValidationException {

		StringBuilder sb = new StringBuilder(
				Validator.validateLong(id, FieldConstants.PERSON_ID, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"), sb.toString()));
		}

		Person person = personDAO.getPerson(id);

		if (person != null) {

			return new ResponseEntity<>(
					new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"), messageSource.getMessage("DESC_SUCCESS"),
							messageSource.getMessage("GET_DESC_SUCCESS"), SetEntitiesIntoDTO.setPersonResponseDTO(person)),
					HttpStatus.OK);
		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("GET_DESC_ERROR_PERSON")));

	}

	public ResponseEntity<ResponseDTO> getPersonWithParameters(Map<String, String> parameters)
			throws ValidationException {

		if (!parameters.isEmpty()) {
			
			Person person = personDAO.getPersonWithParameters(parameters);

			if (person != null) {

				return new ResponseEntity<>(
						new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"), messageSource.getMessage("DESC_SUCCESS"),
								messageSource.getMessage("GET_DESC_SUCCESS"), SetEntitiesIntoDTO.setPersonResponseDTO(person)),
						HttpStatus.OK);
			}
			throw new ValidationException(
					new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("GET_DESC_ERROR_PERSON")));
			
		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_QUERY_PARAMS")));

	}

	public ResponseEntity<ResponseDTO> createPerson(CreatePersonDTO person) throws ValidationException {

		StringBuilder sb = new StringBuilder();
		sb.append(Validator.valideEmail(FieldConstants.PERSON_EMAIL, person.getEmail()));
		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR_VALIDATION"), sb.toString()));
		}

		if (Validator.validateBirthday(person.getBirthday())) {

			if (person.getProfileId().equals(Constants.TOURIST_PROFILE_ID)) {

				if (!personDAO.existPersonTourist(person.getEmail())) {
					createPersonExecute(person);
				} else {
					throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR_VALIDATION"),
							messageSource.getMessage("EXISTING_TOURIST_DESC_ERROR")));
				}
			}
			// At this part the new Person should be a Guide
			if (!StringUtils.isBlank(person.getIdentification()) && !StringUtils.isBlank(person.getRnt())) {
				if (!personDAO.existPersonGuide(person.getIdentification(), person.getRnt(), person.getEmail())) {
					createPersonExecute(person);
				} else {
					throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR_VALIDATION"),
							messageSource.getMessage("EXISTING_GUIDE_DESC_ERROR")));
				}
			}
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR_VALIDATION"),
					messageSource.getMessage("GUIDE_REQUIRED_PARAMS")));
		} else {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR_VALIDATION"),
					messageSource.getMessage("BIRTHDAY_DESC_ERROR")));
		}

	}

	public ResponseEntity<ResponseDTO> updatePerson(Map<String, String> bodyParameters) throws ValidationException {

		if (!bodyParameters.isEmpty()) {

			String identifier = "";
			StringBuilder sb = new StringBuilder();

			if (bodyParameters.containsKey(FieldConstants.PERSON_EMAIL)) {
				identifier = FieldConstants.PERSON_EMAIL;
			} else {
				if (bodyParameters.containsKey(FieldConstants.PERSON_IDENTIFICATION)) {
					identifier = FieldConstants.PERSON_IDENTIFICATION;
				}
			}

			for (String parameter : bodyParameters.keySet()) {

				switch (parameter) {
				case FieldConstants.PERSON_EMAIL:
					sb.append(Validator.valideEmail(FieldConstants.PERSON_EMAIL, bodyParameters.get(parameter)));
					break;
				case FieldConstants.PERSON_PHONENUMBER:
					sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.PERSON_PHONENUMBER,
							FieldConstants.PERSON_PHONENUMBER_LENGTH, FieldConstants.PERSON_PHONENUMBER_OBLIGATORY));
					break;
				case FieldConstants.PERSON_ADDRESS:
					sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.PERSON_ADDRESS,
							FieldConstants.PERSON_ADDRESS_LENGTH, FieldConstants.PERSON_ADDRESS_OBLIGATORY));
					break;
				case FieldConstants.PERSON_PASSWORD:
					sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.PERSON_PASSWORD,
							FieldConstants.PERSON_PASSWORD_LENGTH, FieldConstants.PERSON_PASSWORD_OBLIGATORY));
					break;
				case FieldConstants.PERSON_CALIFICATION:
					sb.append(Validator.validateNumber(("" + bodyParameters.get(parameter)),
							FieldConstants.PERSON_CALIFICATION, FieldConstants.PERSON_CALIFICATION_LENGTH,
							FieldConstants.PERSON_CALIFICATION_OBLIGATORY));
					break;
				case FieldConstants.PERSON_TOKEN:
					sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.PERSON_TOKEN,
							FieldConstants.PERSON_TOKEN_LENGTH, FieldConstants.PERSON_TOKEN_OBLIGATORY));
					break;
				case FieldConstants.PERSON_PROFILEPHOTO:
					sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.PERSON_PROFILEPHOTO,
							FieldConstants.PERSON_PROFILEPHOTO_LENGTH, FieldConstants.PERSON_PROFILEPHOTO_OBLIGATORY));
					break;
				case FieldConstants.PROFILE_ID:
					sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)),
							FieldConstants.PROFILE_ID, FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));
					break;
				case FieldConstants.STATE_ID:
					sb.append(Validator.validateNumber(String.valueOf(bodyParameters.get(parameter)),
							FieldConstants.STATE_ID, FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));
					break;
				default:
					break;
				}

			}

			if (sb.toString().length() > 0) {
				throw new ValidationException(
						new ErrorDTO(messageSource.getMessage("CODE_ERR_VALIDATION"), sb.toString()));
			}

			if (StringUtils.isEmpty(identifier)) {
				throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
						messageSource.getMessage("MISS_IDENTIFICATION_PARAM")));
			}

			boolean updateResponse = personDAO.updatePerson(bodyParameters, identifier);
			if (updateResponse) {
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("PUT_DESC_SUCCESS"),
						updateResponse), HttpStatus.PRECONDITION_REQUIRED);
			}

			return new ResponseEntity<>(
					new ResponseDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("DESC_ERR"),
							messageSource.getMessage("PUT_DESC_ERROR"), updateResponse),
					HttpStatus.PRECONDITION_REQUIRED);

		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_BODY_PARAMS")));
	}

	private ResponseEntity<ResponseDTO> createPersonExecute(CreatePersonDTO personDTO) throws ValidationException {

		Person person = setPerson(personDTO);
		boolean createResponse = personDAO.createPerson(person);
		if (createResponse) {
			return new ResponseEntity<>(
					new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"), messageSource.getMessage("DESC_SUCCESS"),
							messageSource.getMessage("POST_DESC_SUCCESS"), createResponse),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERROR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("POST_DESC_ERROR"), createResponse),
				HttpStatus.OK);
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
			person.setState(state);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return person;
	}

}
