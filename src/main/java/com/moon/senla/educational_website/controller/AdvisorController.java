package com.moon.senla.educational_website.controller;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.moon.senla.educational_website.error.AuthException;
import com.moon.senla.educational_website.error.NotFoundException;
import com.moon.senla.educational_website.error.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AdvisorController {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException validationException) {
        return ErrorResponse.builder()
            .message(validationException.getMessage())
            .status(BAD_REQUEST)
            .timestamp(now())
            .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException notFoundException) {
        return ErrorResponse.builder()
            .message(notFoundException.getMessage())
            .status(HttpStatus.NOT_FOUND)
            .timestamp(now())
            .build();
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(FORBIDDEN)
    public ErrorResponse handleNotFoundException(AuthException authException) {
        return ErrorResponse.builder()
            .message(authException.getMessage())
            .status(HttpStatus.FORBIDDEN)
            .timestamp(now())
            .build();
    }
}
