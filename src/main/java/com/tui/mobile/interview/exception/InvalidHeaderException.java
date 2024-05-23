package com.tui.mobile.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidHeaderException extends ResponseStatusException {

    public InvalidHeaderException(String message) {
        super(HttpStatus.NOT_ACCEPTABLE, message);
    }
}