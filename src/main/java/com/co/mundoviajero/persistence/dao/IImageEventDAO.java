package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.dto.event.ImageEventDTO;

public interface IImageEventDAO {
	
	ImageEventDTO getImageEvent(Long id);
	List<ImageEventDTO> getAllImageEvent(Long idEvent);
	boolean createImageEvent(List<String> images, Long eventId);
	boolean updateImageEvent(Map<String, String> parameters, Long identifier);

}
