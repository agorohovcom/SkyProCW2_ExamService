package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.UnsupportedExamServiceOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.agorohov.skyprocw2examservice.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathQuestionServiceTest {

    private MathQuestionService out;

    @BeforeEach
    void setUp() {
        out = new MathQuestionService(new Random());
    }

    @Test
    void shouldThrowExceptionWhenInvokedAddRemoveOrGetAll() {
        assertThrows(UnsupportedExamServiceOperationException.class,
                () -> out.add(QUESTION_TEXT, QUESTION_ANSWER));
        assertThrows(UnsupportedExamServiceOperationException.class,
                () -> out.add(QUESTION_1));
        assertThrows(UnsupportedExamServiceOperationException.class,
                () -> out.remove(QUESTION_1));
        assertThrows(UnsupportedExamServiceOperationException.class,
                () -> out.getAll());
    }
}