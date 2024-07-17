package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.ParamIsNotPresentException;
import com.agorohov.skyprocw2examservice.exception.QuestionInNotExistException;
import com.agorohov.skyprocw2examservice.exception.QuestionIsAlreadyExistException;
import com.agorohov.skyprocw2examservice.repository.JavaQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.agorohov.skyprocw2examservice.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @InjectMocks
    private JavaQuestionService out;

    @Mock
    private JavaQuestionRepository questionRepositoryMock;

    @BeforeEach
    void setUp() {
        when(questionRepositoryMock.getAll()).thenReturn(ALL_QUESTIONS);
    }

    @Test
    void shouldAddQuestionCorrectly() {
        assertThrows(ParamIsNotPresentException.class, () -> out.add(null));
        assertThrows(ParamIsNotPresentException.class, () -> out.add(EMPTY_STRING, QUESTION_ANSWER));
        assertThrows(ParamIsNotPresentException.class, () -> out.add(null, QUESTION_ANSWER));
        assertThrows(ParamIsNotPresentException.class, () -> out.add(QUESTION_TEXT, EMPTY_STRING));
        assertThrows(ParamIsNotPresentException.class, () -> out.add(QUESTION_TEXT, null));

        when(questionRepositoryMock.add(NO_SUCH_QUESTION)).thenReturn(NO_SUCH_QUESTION);
        assertEquals(NO_SUCH_QUESTION, out.add(NO_SUCH_QUESTION));
        assertThrows(QuestionIsAlreadyExistException.class, () -> out.add(SAME_QUESTION));
    }

    @Test
    void shouldRemoveQuestionCorrectly() {
        assertThrows(ParamIsNotPresentException.class, () -> out.remove(null));

        when(questionRepositoryMock.remove(SAME_QUESTION)).thenReturn(SAME_QUESTION);
        assertEquals(SAME_QUESTION, out.remove(SAME_QUESTION));
        assertThrows(QuestionInNotExistException.class, () -> out.remove(NO_SUCH_QUESTION));
    }

    @Test
    void shouldGetAllQuestionsCorrectly() {
        assertEquals(ALL_QUESTIONS, out.getAll());

        when(questionRepositoryMock.getAll()).thenReturn(EMPTY_QUESTIONS_COLLECTION);
        assertEquals(EMPTY_QUESTIONS_COLLECTION, out.getAll());
    }
}