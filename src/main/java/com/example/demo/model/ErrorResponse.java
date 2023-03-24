package com.example.demo.model;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private final HttpStatus status;
    private final String message;
    private final Object errors;

    public ErrorResponse(HttpStatus status, String message, Object errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getErrors() {
        return errors;
    }
}
