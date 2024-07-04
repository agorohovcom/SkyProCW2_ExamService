package com.agorohov.skyprocw2examservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class ExamServiceExceptionHandler {

    @ExceptionHandler({NotCorrectAmountQuestionsGetFromRepositoryException.class,})
    public ResponseEntity<String> handleEmployeeExceptions(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

}
