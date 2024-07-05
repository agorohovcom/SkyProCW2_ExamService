package com.agorohov.skyprocw2examservice.exception;

public class QuestionInNotExistException extends RuntimeException {
    public QuestionInNotExistException(String message) {
        super(message);
    }
}
