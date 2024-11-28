package com.projects.town_chale.exception;

import com.projects.town_chale.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidBusDetailsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBusDetailsException(InvalidBusDetailsException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(true)
                .details("Invalid Bus details provided")
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleUserAuthenticationException(UserAuthenticationException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(true)
                .details("Invalid User details provided")
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
