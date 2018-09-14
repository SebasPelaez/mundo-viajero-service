package com.co.mundoviajero.util.exception;

public class InvalidTokenException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidTokenException() {
        super();
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTokenException(Throwable cause) {
        super(cause);
    }
}