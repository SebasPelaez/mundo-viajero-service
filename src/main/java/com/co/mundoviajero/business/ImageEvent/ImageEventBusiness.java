package com.co.mundoviajero.business.ImageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.co.mundoviajero.dto.event.imageevent.CreateImageEventDTO;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.mundoviajero.business.SetDTOIntoEntities;
import com.co.mundoviajero.business.SetEntitiesIntoDTO;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.dto.event.imageevent.ImageEventResponseDTO;
import com.co.mundoviajero.persistence.dao.IImageEventDAO;
import com.co.mundoviajero.persistence.entity.ImageEvent;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.Validator;
import com.co.mundoviajero.util.exception.ValidationException;
import com.co.mundoviajero.util.exception.dto.ErrorDTO;

@Service
public class ImageEventBusiness {

	@Autowired
	private MessageSourceAccessor messageSource;

	@Autowired
	private IImageEventDAO imageEventDAO;

	public ResponseEntity<ResponseDTO> getImageEvent(Long id) throws ValidationException {

		StringBuilder sb = new StringBuilder(
				Validator.validateLong(id, FieldConstants.EVENT_ID, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"), sb.toString()));
		}

		ImageEvent imageEvent = imageEventDAO.getImageEvent(id);

		if (imageEvent != null) {
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"),
					SetEntitiesIntoDTO.setImageEventDTO(imageEvent)), HttpStatus.OK);
		}
		throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("GET_DESC_ERROR_IMAGE_EVENT")));
	}

	public ResponseEntity<ResponseDTO> getAllImagesForEvent(Long id) throws ValidationException {

		StringBuilder sb = new StringBuilder(
				Validator.validateLong(id, FieldConstants.EVENT_ID, FieldConstants.ID_OBLIGATORY));

		if (sb.toString().length() > 0) {
			throw new ValidationException(new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"), sb.toString()));
		}

		List<ImageEvent> images = imageEventDAO.getAllImageEvent(id);
		if (CollectionUtils.isNotEmpty(images)) {

			List<ImageEventResponseDTO> imagesDTO = new ArrayList<>();
			images.forEach(image -> imagesDTO.add(SetEntitiesIntoDTO.setImageEventDTO(image)));

			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
					messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("GET_DESC_SUCCESS"), imagesDTO),
					HttpStatus.OK);

		}
		return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
				messageSource.getMessage("DESC_ERR"), messageSource.getMessage("GET_DESC_ERROR"), null),
				HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseDTO> updateImageEvent(Map<String, String> bodyParameters) throws ValidationException {

		Long identifier;
		StringBuilder sb = new StringBuilder();

		if (!bodyParameters.isEmpty()) {

			if (bodyParameters.containsKey(FieldConstants.EVENT_ID)) {
				bodyParameters.remove(FieldConstants.EVENT_ID);
			}
			if (bodyParameters.containsKey(FieldConstants.ID)) {

				identifier = Long.parseLong(bodyParameters.get(FieldConstants.ID));
				bodyParameters.remove(FieldConstants.ID);

				if (!bodyParameters.isEmpty()) {

					for (String parameter : bodyParameters.keySet()) {
						switch (parameter) {

						case FieldConstants.IMAGE_EVENT_PATH:
							sb.append(Validator.valideString(bodyParameters.get(parameter),
									FieldConstants.IMAGE_EVENT_PATH, FieldConstants.IMAGE_EVENT_PATH_LENGTH,
									FieldConstants.IMAGE_EVENT_PATH_OBLIGATORY));
							break;

						case FieldConstants.IMAGE_EVENT_UPLOAD_DATE:
							sb.append(Validator.valideString(bodyParameters.get(parameter),
									FieldConstants.IMAGE_EVENT_UPLOAD_DATE,
									FieldConstants.IMAGE_EVENT_UPLOAD_DATE_LENGTH,
									FieldConstants.IMAGE_EVENT_UPLOAD_DATE_OBLIGATORY));
							break;
						default:
							break;
						}
					}

					if (sb.toString().length() > 0) {
						throw new ValidationException(
								new ErrorDTO(messageSource.getMessage("MISS_QUERY_PARAMS"), sb.toString()));
					}

					if (imageEventDAO.updateImageEvent(bodyParameters, identifier)) {
						return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
								messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("PUT_DESC_SUCCESS"),
								null), HttpStatus.OK);
					}
					return new ResponseEntity<>(
							new ResponseDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("DESC_ERR"),
									messageSource.getMessage("PUT_DESC_ERROR"), null),
							HttpStatus.PRECONDITION_REQUIRED);

				}
				throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
						messageSource.getMessage("UPDATE_EVENT_MORE_EXPECTED_PARAMS")));
			} else {
				throw new ValidationException(new ErrorDTO(messageSource.getMessage("CODE_ERR"),
						messageSource.getMessage("MISS_EVENT_PLACE_ID")));
			}

		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_BODY_PARAMS")));
	}

	public ResponseEntity<ResponseDTO> uploadImage(CreateImageEventDTO createImageEventDTO) throws ValidationException {

		if (createImageEventDTO != null) {
			List<ImageEvent> images = SetDTOIntoEntities.setImageEvent(createImageEventDTO);
			
			boolean upload = imageEventDAO.createImageEvent(images);
			if(upload) {
				return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_SUCCESS"),
						messageSource.getMessage("DESC_SUCCESS"), messageSource.getMessage("POST_DESC_SUCCESS"), upload),
						HttpStatus.PRECONDITION_REQUIRED);
			}
			return new ResponseEntity<>(new ResponseDTO(messageSource.getMessage("CODE_ERR"),
					messageSource.getMessage("DESC_ERR"), messageSource.getMessage("POST_DESC_ERROR"), upload),
					HttpStatus.PRECONDITION_REQUIRED);
		}
		throw new ValidationException(
				new ErrorDTO(messageSource.getMessage("CODE_ERR"), messageSource.getMessage("MISS_BODY_PARAMS")));		

	}

}
