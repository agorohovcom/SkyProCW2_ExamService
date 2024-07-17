package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.ParamIsNotPresentException;
import com.agorohov.skyprocw2examservice.exception.QuestionInNotExistException;
import com.agorohov.skyprocw2examservice.exception.QuestionIsAlreadyExistException;
import com.agorohov.skyprocw2examservice.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static com.agorohov.skyprocw2examservice.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    private QuestionService out;

    @Mock
    private Random random;

    @BeforeEach
    void setUp() {
        out = new MathQuestionService(random);
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

    @Test
    void shouldGetRandomQuestionCorrectly() {
        when(random.nextInt(1000)).thenReturn(25);

        when(random.nextInt(4)).thenReturn(0);
        String questionText = "Сколько будет 25 + 25?";
        String questionAnswer = "Ответ: 50,00";
        Question expected = new Question(questionText, questionAnswer);
        assertEquals(expected, out.getRandomQuestion());

        when(random.nextInt(4)).thenReturn(1);
        questionText = "Сколько будет 25 - 25?";
        questionAnswer = "Ответ: 0,00";
        expected = new Question(questionText, questionAnswer);
        assertEquals(expected, out.getRandomQuestion());

        when(random.nextInt(4)).thenReturn(2);
        questionText = "Сколько будет 25 * 25?";
        questionAnswer = "Ответ: 625,00";
        expected = new Question(questionText, questionAnswer);
        assertEquals(expected, out.getRandomQuestion());

        when(random.nextInt(4)).thenReturn(3);
        questionText = "Сколько будет 25 / 25?";
        questionAnswer = "Ответ: 1,00";
        expected = new Question(questionText, questionAnswer);
        assertEquals(expected, out.getRandomQuestion());
    }
}