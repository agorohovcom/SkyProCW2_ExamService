package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.NotCorrectAmountQuestionsGetFromRepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import static com.agorohov.skyprocw2examservice.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    private ExaminerServiceImpl out;

    @Mock
    private QuestionService service1;
    @Mock
    private QuestionService service2;
    @Mock
    private QuestionService service3;

    static int questionsCount;

    private List<QuestionService> questionServices;

    @BeforeEach
    void setUp() {
        questionServices = List.of(service1, service2, service3);
        out = new ExaminerServiceImpl(questionServices, new Random());

        when(service1.getAll()).thenReturn(List.of(QUESTION_1, QUESTION_2));
        when(service2.getAll()).thenReturn(List.of(QUESTION_3));
        when(service3.getAll()).thenReturn(EMPTY_QUESTIONS_COLLECTION);

        questionsCount = questionServices.stream()
                .filter(Objects::nonNull)
                .mapToInt(e -> e.getAll().size())
                .sum();
    }

    @Test
    void mocksTest() {
        assertNotNull(service1);
        assertNotNull(service2);
        assertNotNull(service3);

        assertEquals(List.of(QUESTION_1, QUESTION_2), service1.getAll());
        assertEquals(List.of(QUESTION_3), service2.getAll());
        assertEquals(EMPTY_QUESTIONS_COLLECTION, service3.getAll());
    }

    @ParameterizedTest
    @MethodSource("amountArray")
    void shouldGetQuestionsCorrectly(int amount) {
        when(service1.getRandomQuestion()).thenReturn(QUESTION_1);
        when(service2.getRandomQuestion()).thenReturn(QUESTION_3);

        assertEquals(QUESTION_1, service1.getRandomQuestion());
        assertEquals(QUESTION_3, service2.getRandomQuestion());

        assertTrue(out.getQuestions(GET_QUESTIONS_RESULT.size()).containsAll(GET_QUESTIONS_RESULT));

        assertThrows(NotCorrectAmountQuestionsGetFromRepositoryException.class,
                () -> out.getQuestions(amount));
    }

    private static Stream<Integer> amountArray() {
        return Stream.of(NEGATIVE, ZERO, MORE_THEN_GET_QUESTIONS_RESULT_SIZE);
    }
}