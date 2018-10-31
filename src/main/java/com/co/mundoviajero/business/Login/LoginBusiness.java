package com.co.mundoviajero.business.Login;

import com.co.mundoviajero.dto.login.AuthenticateDTO;
import com.co.mundoviajero.dto.person.PersonResponseDTO;
import com.co.mundoviajero.persistence.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.business.SetEntitiesIntoDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.login.LoginResponseDTO;
import com.co.mundoviajero.persistence.dao.IPersonDAO;
import com.co.mundoviajero.util.exception.ValidationException;

@Service
public class LoginBusiness {

    @Autowired
    private IPersonDAO personDAO;

    @Autowired
    private MessageSourceAccessor messageSource;

    public ResponseEntity<ResponseDTO> login(AuthenticateDTO loginParameters) throws ValidationException {

        Person person = personDAO.login(loginParameters.getEmail(),loginParameters.getPassword());

        if (person != null) {

            PersonResponseDTO personResponseDTO = SetEntitiesIntoDTO.setPersonResponseDTO(person);

            String jwt = TokenBusiness.generateToken(person.getEmail());
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(personResponseDTO, jwt);

            return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
                    messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("LOGIN_SUCCESS"),
                    loginResponseDTO),HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("LOGIN_ERROR_CODE"),
                messageSource.getMessage("LOGIN_ERROR"), messageSource.getMessage("LOGIN_ERROR_DESC"),
                null),HttpStatus.UNAUTHORIZED);

    }

}
