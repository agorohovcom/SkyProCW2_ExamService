package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.NotCorrectAmountQuestionsGetFromRepositoryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static com.agorohov.skyprocw2examservice.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @InjectMocks
    ExaminerServiceImpl out;

    @Mock
    private List<QuestionService> questionServices;
    @Mock
    private QuestionService service1;
    @Mock
    private QuestionService service2;
    @Mock
    private Random random = new Random();

    @Test
    void shouldReturnQuestionsWhichContainsInRepository() {
        when(questionServices.size()).thenReturn(2);
        when(questionServices.get(0)).thenReturn(service1);
        when(questionServices.get(1)).thenReturn(service2);

        when(service1.getAll()).thenReturn(List.of(QUESTION_1, QUESTION_2));
        when(service2.getAll()).thenReturn(List.of(QUESTION_3));

        when(service1.getRandomQuestion()).thenReturn(QUESTION_1);
        when(service2.getRandomQuestion()).thenReturn(QUESTION_3);


        assertEquals(2, questionServices.size());
        assertEquals(service1, questionServices.get(0));
        assertEquals(service2, questionServices.get(1));

        assertEquals(List.of(QUESTION_1, QUESTION_2), service1.getAll());
        assertEquals(List.of(QUESTION_3), service2.getAll());

        assertEquals(QUESTION_1, service1.getRandomQuestion());
        assertEquals(QUESTION_3, service2.getRandomQuestion());


//        when(out.getQuestions(anyInt())).thenReturn(List.of(QUESTION_1, QUESTION_3));
//        assertTrue(out.getQuestions(2).containsAll(List.of(QUESTION_1, QUESTION_3)));
    }

    @ParameterizedTest
    @MethodSource("amountArray")
    void getQuestionsShouldThrowExceptionIfAmountIsIncorrect(int amount) {
        assertThrows(NotCorrectAmountQuestionsGetFromRepositoryException.class,
                () -> out.getQuestions(amount));
    }

    private static Stream<Integer> amountArray() {
        return Stream.of(NEGATIVE, ZERO, MORE_THAN_SIZE);
    }
}