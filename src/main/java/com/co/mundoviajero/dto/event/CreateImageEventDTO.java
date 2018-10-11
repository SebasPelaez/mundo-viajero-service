package com.co.mundoviajero.dto.event;

import java.io.Serializable;
import java.util.List;

public class CreateImageEventDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long eventId;
    private List<String> images;

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
}
