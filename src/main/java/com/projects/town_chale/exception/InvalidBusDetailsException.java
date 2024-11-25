package com.projects.town_chale.exception;

public class InvalidBusDetailsException extends RuntimeException {
    public InvalidBusDetailsException(String message) {
        super(message);
    }

    public InvalidBusDetailsException(String message, Throwable cause) {
        super(message, cause);
    }
}
