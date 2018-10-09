package com.co.mundoviajero.persistence.dao;

import java.util.List;
import java.util.Map;

import com.co.mundoviajero.dto.ImageEventDTO;

public interface IImageEventDAO {
	
	ImageEventDTO getImageEvent(Long id);
	List<ImageEventDTO> getAllImageEvent(Long idEvent);
	boolean createImageEvent(List<ImageEventDTO> imageEventDTO, Long eventId);
	boolean updateImageEvent(Map<String, String> parameters, Long identifier);

}
