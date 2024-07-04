package com.agorohov.skyprocw2examservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class ExamServiceExceptionHandler {

    @ExceptionHandler({
            NotCorrectAmountQuestionsGetFromRepositoryException.class
    })
    public String handleEmployeeExceptions(RuntimeException e) {
        e.printStackTrace();
        return e.getMessage();
    }
}
