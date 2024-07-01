package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
