package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.UnsupportedExamServiceOperationException;
import com.agorohov.skyprocw2examservice.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
@Qualifier("mathQuestionService")
public class MathQuestionService implements QuestionService {

    private final Random random;

    public MathQuestionService(Random random) {

        this.random = random;
    }

    @Override
    public Question add(String question, String answer) {
        throwUnsupportedExamServiceOperationException();
        return null;
    }

    @Override
    public Question add(Question question) {
        throwUnsupportedExamServiceOperationException();
        return null;
    }

    @Override
    public Question remove(Question question) {
        throwUnsupportedExamServiceOperationException();
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        throwUnsupportedExamServiceOperationException();
        return null;
    }

    @Override
    public Question getRandomQuestion() {
        int num1 = random.nextInt(1000);
        int num2 = random.nextInt(1000);
        long answer = 0;
        char operation = ' ';
        switch (random.nextInt(4)) {
            case 0:
                operation = '+';
                answer = num1 + num2;
                break;
            case 1:
                operation = '-';
                answer = num1 - num2;
                break;
            case 2:
                operation = '*';
                answer = num1 * num2;
                break;
            case 3:
                operation = '/';
                while (num2 == 0) {
                    num2 = random.nextInt(1000);
                }
                answer = num1 / num2;
                break;
        }
        ;
        String questionText = String.format("Сколько будет %d %s %d", num1, operation, num2);
        String questionAnswer = String.format("Ответ: %d", answer);
        return new Question(questionText, questionAnswer);
    }

    private void throwUnsupportedExamServiceOperationException() {
        throw new UnsupportedExamServiceOperationException("Метод не поддерживается");
    }
}
