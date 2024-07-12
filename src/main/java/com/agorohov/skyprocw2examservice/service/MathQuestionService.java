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

        return null;
    }

    private void throwUnsupportedExamServiceOperationException() {
        throw new UnsupportedExamServiceOperationException("Метод не поддерживается");
    }
}
