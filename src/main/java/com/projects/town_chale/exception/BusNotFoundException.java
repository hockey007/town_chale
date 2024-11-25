package com.projects.town_chale.exception;

public class BusNotFoundException extends RuntimeException {
    public BusNotFoundException(String message) {
        super(message);
    }

    public BusNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
