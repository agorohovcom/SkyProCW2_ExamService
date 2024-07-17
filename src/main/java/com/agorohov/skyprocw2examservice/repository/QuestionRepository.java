package com.agorohov.skyprocw2examservice.repository;

import com.agorohov.skyprocw2examservice.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
