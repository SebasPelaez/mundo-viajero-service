package com.co.mundoviajero.business.Login;

import java.util.Date;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.LoginDTO;
import com.co.mundoviajero.dto.PersonDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
	public Response login(LoginDTO login) throws ValidationException {
		StringBuilder sb = new StringBuilder();
		
		sb.append(Validator.valideEmail(FieldConstants.PERSON_EMAIL, login.getEmail()));		

		sb.append(Validator.valideString(login.getPassword(), FieldConstants.PERSON_PASSWORD,
				FieldConstants.PERSON_PASSWORD_LENGTH, FieldConstants.PERSON_PASSWORD_OBLIGATORY));

		
		PersonDTO personDTO = personDAO.login(login);

		if (personDTO != null) {
			//Llave de prueba
			String key = "prueba";
			Long time = System.currentTimeMillis();
			String jwt = Jwts.builder()
					.signWith(SignatureAlgorithm.HS256, key)
					.setSubject("Juan")
					.setIssuedAt(new Date(time))
					.claim("email", personDTO.getEmail())
					.claim("password", personDTO.getPassword())
					.compact();
			JsonObject json = Json.createObjectBuilder()
								  .add("JWT", jwt).build();
			
			return Response.status(Response.Status.CREATED).entity(json).build();
			/*return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), personDTO),
					HttpStatus.OK);*/
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
		/*return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("USER_NOT_FOUND"), null),
				HttpStatus.NOT_FOUND);*/

	}
	
	
	
}
