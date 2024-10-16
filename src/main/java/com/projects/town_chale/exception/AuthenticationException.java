package com.projects.town_chale.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationException extends RuntimeException {

    private HttpStatus status;

    public AuthenticationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
