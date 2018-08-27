package com.co.mundoviajero.dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private String codeStatus;

    private String descStatus;

    private String message;

    private Object response;

    public ResponseDTO() {
        /* no-code */
    }

    public ResponseDTO(String codeStatus, String descStatus, String message) {
        this.codeStatus = codeStatus;
        this.descStatus = descStatus;
        this.message = message;
    }

    public ResponseDTO(String codeStatus, String descStatus, String message, Object response) {
        this.codeStatus = codeStatus;
        this.descStatus = descStatus;
        this.message = message;
        this.response = response;
    }
    
    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getDescStatus() {
        return descStatus;
    }

    public void setDescStatus(String descStatus) {
        this.descStatus = descStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

}
