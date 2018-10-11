package com.co.mundoviajero.dto.event;

import java.io.Serializable;

public class ImageEventDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long eventId;
	private String uploadDate;
	private String imagePath;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
