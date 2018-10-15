package com.co.mundoviajero.business.ImageEvent;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.dto.event.CreateImageEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.event.ImageEventDTO;
import com.co.mundoviajero.persistence.dao.IImageEventDAO;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
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
	
	public ResponseEntity<ResponseDTO> getAllImagesForEvent(Long id) {
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
		
		if (bodyParameters.containsKey(FieldConstants.ID)) {
			
			identifier = Long.parseLong(bodyParameters.get(FieldConstants.ID));
			bodyParameters.remove(FieldConstants.ID);
			
			if(!bodyParameters.isEmpty()){
				
				for (String parameter : bodyParameters.keySet()) {
					switch (parameter) {
					
						case FieldConstants.IMAGE_EVENT_PATH:
							sb.append(Validator.valideString(bodyParameters.get(parameter),
									FieldConstants.IMAGE_EVENT_PATH, FieldConstants.IMAGE_EVENT_PATH_LENGTH,
									FieldConstants.IMAGE_EVENT_PATH_OBLIGATORY));
							break;
	
						case FieldConstants.IMAGE_EVENT_UPLOAD_DATE:
							sb.append(Validator.valideString(bodyParameters.get(parameter), FieldConstants.IMAGE_EVENT_UPLOAD_DATE,
									FieldConstants.IMAGE_EVENT_UPLOAD_DATE_LENGTH, FieldConstants.IMAGE_EVENT_UPLOAD_DATE_OBLIGATORY));
							break;
						default:
							break;
					}
				}
				
				if (sb.toString().length() > 0) {
					throw new ValidationException(sb.toString());
				}
				
				if (imageEventDAO.updateImageEvent(bodyParameters, identifier)) {
					return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
							messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("PUT_DESC_SUCCESS"), null),
							HttpStatus.OK);
				}
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
						messageSource.getMessage("DESC_ERR"), messageSource.getMessage("PUT_DESC_ERROR"), null),
						HttpStatus.PRECONDITION_REQUIRED);
				
			}
			throw new ValidationException(messageSource.getMessage("UPDATE_EVENT_MORE_EXPECTED_PARAMS"));
		}else {
			throw new ValidationException(messageSource.getMessage("MISS_EVENT_PLACE_ID"));
		}
	}
	
	public ResponseEntity<ResponseDTO> uploadImage(CreateImageEventDTO createImageEventDTO) throws ValidationException {
		
		StringBuilder sb = new StringBuilder();

		for (String image: createImageEventDTO.getImages()) {

			sb.append(Validator.valideString(image, FieldConstants.IMAGE_EVENT_PATH,
					FieldConstants.IMAGE_EVENT_PATH_LENGTH, FieldConstants.IMAGE_EVENT_PATH_OBLIGATORY));
		}
		
		sb.append(Validator.validateNumber(String.valueOf(createImageEventDTO.getEventId()), FieldConstants.EVENT_ID,
				FieldConstants.ID_LENGTH, FieldConstants.ID_OBLIGATORY));
		
		if (sb.toString().length() > 0) {
			throw new ValidationException(sb.toString());
		}

		if (imageEventDAO.createImageEvent(createImageEventDTO.getImages(), createImageEventDTO.getEventId())) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("POST_DESC_SUCCESS"), true),
					HttpStatus.PRECONDITION_REQUIRED);
		}

		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("POST_DESC_ERROR"), null),
				HttpStatus.PRECONDITION_REQUIRED);
		
	}
	
}
