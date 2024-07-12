package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.ParamIsNotPresentException;
import com.agorohov.skyprocw2examservice.exception.QuestionInNotExistException;
import com.agorohov.skyprocw2examservice.exception.QuestionIsAlreadyExistException;
import com.agorohov.skyprocw2examservice.model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("mathQuestionService")
public class MathQuestionService implements QuestionService {

    private final Set<Question> questions;
    private final Random random;

    public MathQuestionService(Random random) {
        questions = new HashSet<>();
        this.random = random;
    }

    @Override
    public Question add(String question, String answer) {
        checkParamIsPresent(question);
        checkParamIsPresent(answer);
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        checkParamIsPresent(question);
        if (questions.contains(question)) {
            throw new QuestionIsAlreadyExistException("Такой вопрос уже добавлен, повторное добавление невозможно");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        checkParamIsPresent(question);
        if (!questions.contains(question)) {
            throw new QuestionInNotExistException("Такого вопроса нет, удаление невозможно");
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return List.copyOf(questions);
    }

    @Override
    public Question getRandomQuestion() {
        Question result = null;
        int num1 = random.nextInt(1000);
        int num2 = random.nextInt(1000);
        double answer = 0;
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
                answer = (double) num1 / num2;
                break;
        }

        String questionText = String.format("Сколько будет %d %s %d", num1, operation, num2);
        String questionAnswer = String.format("Ответ: %.2f", answer);

        result = new Question(questionText, questionAnswer);
        questions.add(result);
        return result;
    }

    private void checkParamIsPresent(Object o) {
        if (o == null || o.toString().isBlank()) {
            throw new ParamIsNotPresentException("Некорректное значение параметра");
        }
    }
}
