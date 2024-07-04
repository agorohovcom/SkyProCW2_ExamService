package com.agorohov.skyprocw2examservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotCorrectAmountQuestionsGetFromRepositoryException extends RuntimeException {
    public NotCorrectAmountQuestionsGetFromRepositoryException(String message) {
        super(message);
    }
}
