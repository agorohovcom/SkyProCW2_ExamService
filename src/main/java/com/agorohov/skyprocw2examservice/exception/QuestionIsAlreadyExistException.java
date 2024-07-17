package com.agorohov.skyprocw2examservice.exception;

public class QuestionIsAlreadyExistException extends RuntimeException {
    public QuestionIsAlreadyExistException(String message) {
        super(message);
    }
}
