package com.thoughtworks.gtb.quiz.exception;

import com.thoughtworks.gtb.quiz.error.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserIsNotExistException.class)
    public ResponseEntity<ErrorResult> handleUserException(UserIsNotExistException e) {
        ErrorResult errorResult = new ErrorResult(new Date(),404,"Not Found",e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(UserEducationsIsNotExistException.class)
    public ResponseEntity<ErrorResult> handleUserException(UserEducationsIsNotExistException e) {
        ErrorResult errorResult = new ErrorResult(new Date(),404,"Not Found",e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }
}
