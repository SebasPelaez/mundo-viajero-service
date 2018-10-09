package com.co.mundoviajero.controller.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.ImageEvent.ImageEventBusiness;
import com.co.mundoviajero.controller.ImageEventController;
import com.co.mundoviajero.dto.ResponseDTO;
import com.co.mundoviajero.util.FieldConstants;
import com.co.mundoviajero.util.exception.ValidationException;

@RestController
public class ImageEventImpl implements ImageEventController{
	
	@Autowired
	private MessageSourceAccessor messageSource;
	
	@Autowired
	private ImageEventBusiness imageEventBusiness;

	@Override
	public ResponseEntity<ResponseDTO> getImageEvent(@PathVariable("id") String id) throws Exception {
		if(StringUtils.isNotBlank(id)) {
			return imageEventBusiness.getImageEvent(Long.parseLong(id));
		}
		throw new ValidationException(messageSource.getMessage("MISS_QUERY_PARAMS"));
	}

	@Override
	public ResponseEntity<ResponseDTO> getAllImagesForEvent(@PathVariable("id") String id) throws Exception {
		if(StringUtils.isNotBlank(id)) {
			return imageEventBusiness.getAllImagesForEvent(Long.parseLong(id));
		}
		throw new ValidationException(messageSource.getMessage("MISS_QUERY_PARAMS"));
	}

	@Override
	public ResponseEntity<ResponseDTO> updateImages(@RequestBody Map<String, String> bodyParameters) throws Exception {
		if(!bodyParameters.isEmpty()){
			if (bodyParameters.containsKey(FieldConstants.EVENT_ID)) {
				bodyParameters.remove(FieldConstants.EVENT_ID);
			}
			return imageEventBusiness.updateImageEvent(bodyParameters);
		}
		throw new ValidationException(messageSource.getMessage("MISS_BODY_PARAMS"));
	}

}
