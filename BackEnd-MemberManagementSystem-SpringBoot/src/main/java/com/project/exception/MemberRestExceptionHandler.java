package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MemberRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<MemberErrorResponse> handleException(MemberNotFoundException exception) {
        MemberErrorResponse errorResponse = new MemberErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value()); // 404
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MemberErrorResponse> handleException(Exception exception) {
        MemberErrorResponse errorResponse = new MemberErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value()); // 400
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
