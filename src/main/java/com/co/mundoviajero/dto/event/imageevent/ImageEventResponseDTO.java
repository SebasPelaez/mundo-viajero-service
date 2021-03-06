package com.co.mundoviajero.dto.event.imageevent;

import java.io.Serializable;

import com.co.mundoviajero.dto.state.StateResponseDTO;

public class ImageEventResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long eventId;
	private String uploadDate;
	private String imagePath;
	private StateResponseDTO state;

	public ImageEventResponseDTO(Long id, Long eventId, String uploadDate, String imagePath, StateResponseDTO state) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.uploadDate = uploadDate;
		this.imagePath = imagePath;
		this.state = state;
	}

	public StateResponseDTO getState() {
		return state;
	}

	public void setState(StateResponseDTO state) {
		this.state = state;
	}

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
