package com.tui.mobile.interview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RepositoriesNotFoundException extends ResponseStatusException {

    public RepositoriesNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
