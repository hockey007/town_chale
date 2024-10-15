package com.projects.town_chale.handler;

import com.projects.town_chale.dto.ResponseWrapper;
import com.projects.town_chale.exception.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseWrapper> handleAuthenticationException(AuthenticationException exception) {
        ResponseWrapper responseWrapper = new ResponseWrapper(true, exception.getMessage());
        return new ResponseEntity<>(responseWrapper, exception.getStatus());
    }

}
