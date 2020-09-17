package com.thoughtworks.gtb.quiz.exception;

// GTB: 不需要这个 exception 的
public class UserEducationsIsNotExistException extends RuntimeException {
    public UserEducationsIsNotExistException(String message) {
        super(message);
    }
}
