package com.agorohov.skyprocw2examservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class ExamServiceExceptionHandler {

    @ExceptionHandler({
            NotCorrectAmountQuestionsGetFromRepositoryException.class,
            QuestionWasNotAddedException.class,
            ParamIsNotPresentException.class,
            QuestionWasNotRemovedException.class,
            QuestionInNotExistException.class,
            QuestionIsAlreadyExistException.class
    })
    public ResponseEntity<String> handleEmployeeExceptions(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(UnsupportedExamServiceOperationException.class)
    public ResponseEntity<String> handleUnsupportedExamServiceOperationException(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(e.getMessage());
    }

}
