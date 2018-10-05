package com.co.mundoviajero.business.Login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.LoginDTO;
import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;

@Service
public class LoginBusiness {

	@Autowired
	private IPersonDAO personDAO;
	
	@Autowired
	private MessageSourceAccessor messageSource;
	
	/**
	 * @param login
	 * @return
	 * @throws ValidationException
	 */
	public ResponseEntity<ResponseDTO> login(Map<String, String> loginParameters) throws ValidationException {
		
		StringBuilder sb = new StringBuilder();
		
		if( loginParameters.containsKey("email") && loginParameters.containsKey("password")) {
			
			sb.append(Validator.valideEmail(FieldConstants.PERSON_EMAIL, loginParameters.get("email")));		

			sb.append(Validator.valideString(loginParameters.get("password"), FieldConstants.PERSON_PASSWORD,
					FieldConstants.PERSON_PASSWORD_LENGTH, FieldConstants.PERSON_PASSWORD_OBLIGATORY));
			
			if (sb.toString().length() > 0) {
				throw new ValidationException(sb.toString());
			}
			
			PersonDTO person = personDAO.login(loginParameters);

			if (person != null) {
				
				String jwt = TokenBusiness.generateToken(person.getEmail());				
				LoginDTO token = new LoginDTO(person, jwt);

				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), token),
						HttpStatus.OK);
			}
			
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("DESC_ERR"), messageSource.getMessage("USER_NOT_FOUND"), null),
					HttpStatus.NOT_FOUND);
			
			
		}else {
			throw new ValidationException("No se dispone de elementos para loguear");
		}
	}
	
}
