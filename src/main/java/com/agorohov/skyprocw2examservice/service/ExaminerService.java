package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
