package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.NotCorrectAmountQuestionsGetFromRepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static com.agorohov.skyprocw2examservice.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @InjectMocks
    ExaminerServiceImpl out;

    @Mock
    private List<QuestionService> javaQuestionServicesMock;

    @BeforeEach
    public void setUp() {
        when(javaQuestionServiceMock.getAll()).thenReturn(ALL_QUESTIONS);
    }

    @Test
    void shouldReturnQuestionsWhichContainsInRepository() {
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(QUESTION_1);
        assertTrue(out.getQuestions(1).contains(QUESTION_1));
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