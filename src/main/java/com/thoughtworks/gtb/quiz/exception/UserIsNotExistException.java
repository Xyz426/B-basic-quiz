package com.thoughtworks.gtb.quiz.exception;

import lombok.Data;

@Data
public class UserIsNotExistException extends RuntimeException{
    public UserIsNotExistException(String message) {
        super(message);
    }
}
