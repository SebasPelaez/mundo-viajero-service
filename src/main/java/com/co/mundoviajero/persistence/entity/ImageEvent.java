package com.co.mundoviajero.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ImageEvent")
public class ImageEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Attributes
	@Id
	@GeneratedValue(generator = "codigo")
	@SequenceGenerator(name = "codigo", sequenceName = "SQ_IMAGEEVENT", allocationSize = 1)
	@Column(name = "Id")
	private Long id;
	
	@Column(name = "EventId")
	private Long eventId;
	
	@Column(name = "ImagePath")
	private String imagePath;

	@Column(name = "UploadDate")
	private Timestamp uploadDate;

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

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getImagePath() {
		return imagePath.trim();
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
