package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.ParamIsNotPresentException;
import com.agorohov.skyprocw2examservice.exception.QuestionInNotExistException;
import com.agorohov.skyprocw2examservice.exception.QuestionIsAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.agorohov.skyprocw2examservice.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class MathQuestionServiceTest {

    private MathQuestionService out;

    @BeforeEach
    void setUp() {
        out = new MathQuestionService(new Random());
        out.add(QUESTION_1);
        out.add(QUESTION_2);
        out.add(QUESTION_3);
    }

    @Test
    void shouldAddQuestionCorrectly() {
        assertThrows(ParamIsNotPresentException.class, () -> out.add(null));
        assertThrows(ParamIsNotPresentException.class, () -> out.add(EMPTY_STRING, QUESTION_ANSWER));
        assertThrows(ParamIsNotPresentException.class, () -> out.add(null, QUESTION_ANSWER));
        assertThrows(ParamIsNotPresentException.class, () -> out.add(QUESTION_TEXT, EMPTY_STRING));
        assertThrows(ParamIsNotPresentException.class, () -> out.add(QUESTION_TEXT, null));

        assertEquals(NO_SUCH_QUESTION, out.add(NO_SUCH_QUESTION));
        assertThrows(QuestionIsAlreadyExistException.class,
                () -> out.add(SAME_QUESTION));
    }

    @Test
    void shouldRemoveQuestionCorrectly() {
        assertThrows(ParamIsNotPresentException.class, () -> out.remove(null));

        assertEquals(SAME_QUESTION, out.remove(SAME_QUESTION));
        assertThrows(QuestionInNotExistException.class, () -> out.remove(NO_SUCH_QUESTION));
    }

    @Test
    void shouldGetAllQuestionsCorrectly() {
        assertNotNull(out.getAll());

        boolean isCollectionsSame = ALL_QUESTIONS.size() == out.getAll().size() &&
                out.getAll().containsAll(ALL_QUESTIONS);
        assertTrue(isCollectionsSame);
    }
}