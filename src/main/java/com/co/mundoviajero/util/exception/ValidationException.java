package com.co.mundoviajero.util.exception;

import com.co.mundoviajero.util.exception.dto.ErrorDTO;

public class ValidationException extends Exception {

    private static final long serialVersionUID = 1L;
    private ErrorDTO errorDTO;

    public ValidationException() {
        super();
    }

    public ValidationException(ErrorDTO errorDTO) {
        super(errorDTO.getMessage());
        this.errorDTO = errorDTO;
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }

    public void setErrorDTO(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

}
