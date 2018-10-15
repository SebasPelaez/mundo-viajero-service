package com.co.mundoviajero.business.Profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.ProfileDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.persistence.dao.IProfileDAO;
import com.co.mundoviajero.util.exception.ValidationException;

@Service
public class ProfileBusiness {
	
	@Autowired
	private IProfileDAO profileDAO;
	
	@Autowired
	private MessageSourceAccessor messageSource;
	
	public ResponseEntity<ResponseDTO> getAllProfiles() throws Exception {
		List<ProfileDTO> profiles = profileDAO.getAllProfiles();
		if (profiles != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), profiles),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseDTO> getProfile(Long searchParameter) throws ValidationException {
		ProfileDTO profile = profileDAO.getProfile(searchParameter);
		
		if(profile != null) {
			
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), profile),
					HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);

	}

}
