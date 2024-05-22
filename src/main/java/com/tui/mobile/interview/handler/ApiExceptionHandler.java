package com.tui.mobile.interview.handler;

import com.tui.mobile.interview.exception.WrongHeaderException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ResponseBody
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleWrongHeaderException(final WrongHeaderException exception){
//        return ResponseEntity.status(406).body(new ErrorResponse(exception.getDupa(), exception.getDupa()));
//    }

    @Getter
    @RequiredArgsConstructor
    static class ErrorResponse {
        private final String code;
        private final String message;
    }
}
