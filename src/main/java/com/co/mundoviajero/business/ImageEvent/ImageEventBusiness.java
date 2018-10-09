package com.co.mundoviajero.business.ImageEvent;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.ImageEventDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.persistence.dao.IImageEventDAO;
import com.co.mundoviajero.util.exception.ValidationException;

@Service
public class ImageEventBusiness {

	@Autowired
	private MessageSourceAccessor messageSource;
	
	@Autowired
	private IImageEventDAO imageEventDAO;
	
	public ResponseEntity<ResponseDTO> getImageEvent(Long id) throws ValidationException {

		ImageEventDTO imageEventDTO = imageEventDAO.getImageEvent(id);

		if (imageEventDTO != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), imageEventDTO),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);

	}
	
	public ResponseEntity<ResponseDTO> getAllImagesEventForEvent(Long id) {
		List<ImageEventDTO> images = imageEventDAO.getAllImageEvent(id);
		if (images != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), images),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseDTO> updateImageEvent(Map<String, String> bodyParameters) throws ValidationException {
		
		Long identifier;
		StringBuilder sb = new StringBuilder();
		return null;
	}
	
	
}
