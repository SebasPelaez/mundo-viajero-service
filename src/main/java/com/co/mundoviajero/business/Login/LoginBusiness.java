package com.co.mundoviajero.business.Login;

import java.security.Key;
import java.util.Date;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.LoginDTO;
import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.TokenDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

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
	public ResponseEntity<ResponseDTO> login(LoginDTO login) throws ValidationException {
		StringBuilder sb = new StringBuilder();
		
		sb.append(Validator.valideEmail(FieldConstants.PERSON_EMAIL, login.getEmail()));		

		sb.append(Validator.valideString(login.getPassword(), FieldConstants.PERSON_PASSWORD,
				FieldConstants.PERSON_PASSWORD_LENGTH, FieldConstants.PERSON_PASSWORD_OBLIGATORY));

		
		PersonDTO person = personDAO.login(login);

		if (person != null) {
			Key key =Keys.secretKeyFor(SignatureAlgorithm.HS256);
			Long time = System.currentTimeMillis();
			String jwt = Jwts.builder()
					.signWith(key)
					.setSubject("Juan")
					.setIssuedAt(new Date(time))
					.claim("email", person.getEmail())
					.claim("password", person.getPassword())
					.compact();
			
			TokenDTO token = new TokenDTO(person, jwt);

			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), token),
					HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("USER_NOT_FOUND"), null),
				HttpStatus.NOT_FOUND);

	}
	
	
	
}
