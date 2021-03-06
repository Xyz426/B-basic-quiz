package com.thoughtworks.gtb.quiz.exception;

import com.thoughtworks.gtb.quiz.error.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

// GTB: - 404, Not Found 这些尽量别 hard code 了，HttpStatus 下有常量可以用
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(new Date(),400,"Bad Request",message));
    }

}
