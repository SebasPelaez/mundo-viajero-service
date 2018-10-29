package com.co.mundoviajero.controller.impl;

import java.util.Map;

import javax.validation.Valid;

import com.co.mundoviajero.dto.event.imageevent.CreateImageEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.co.mundoviajero.business.ImageEvent.ImageEventBusiness;
import com.co.mundoviajero.controller.ImageEventController;
import com.co.mundoviajero.dto.ResponseDTO;

@RestController
public class ImageEventImpl implements ImageEventController{
		
	@Autowired
	private ImageEventBusiness imageEventBusiness;

	@Override
	public ResponseEntity<ResponseDTO> getImageEvent(@PathVariable("id") Long id) throws Exception {
		return imageEventBusiness.getImageEvent(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> getAllImagesForEvent(@PathVariable("id") Long id) throws Exception {
		return imageEventBusiness.getAllImagesForEvent(id);
	}

	@Override
	public ResponseEntity<ResponseDTO> updateImages(@RequestBody Map<String, String> bodyParameters) throws Exception {
		return imageEventBusiness.updateImageEvent(bodyParameters);
	}

	@Override
	public ResponseEntity<ResponseDTO> uploadImage(@Valid @RequestBody CreateImageEventDTO createImageEventDTO) throws Exception {
		return imageEventBusiness.uploadImage(createImageEventDTO);
	}

}
