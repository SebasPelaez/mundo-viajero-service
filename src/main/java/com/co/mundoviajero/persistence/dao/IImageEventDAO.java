package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.persistence.entity.ImageEvent;

public interface IImageEventDAO {
	
	ImageEvent getImageEvent(Long id);
	List<ImageEvent> getAllImageEvent(Long idEvent);
	boolean createImageEvent(List<ImageEvent> images);
	boolean updateImageEvent(Map<String, String> parameters, Long identifier);

}
