package com.co.mundoviajero.dto.event.imageevent;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class CreateImageEventDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long eventId;

    @NotNull
    @NotEmpty
    private List<String> images;
    
    @NotNull
	private String stateId;
    
    public CreateImageEventDTO() {}

	public CreateImageEventDTO(Long eventId, List<String> images,String stateId) {
		this.eventId = eventId;
		this.images = images;
		this.stateId = stateId;
	}

	public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
}
