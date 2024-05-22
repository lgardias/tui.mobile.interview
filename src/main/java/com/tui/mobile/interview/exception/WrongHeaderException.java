package com.tui.mobile.interview.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponseException;

@Getter
@AllArgsConstructor
@Setter
public class WrongHeaderException extends RuntimeException {
    private String dupa;
}
