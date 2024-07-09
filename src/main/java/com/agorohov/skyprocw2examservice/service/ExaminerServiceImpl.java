package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.NotCorrectAmountQuestionsGetFromRepositoryException;
import com.agorohov.skyprocw2examservice.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> questionServices;
    private final Random random;

    public ExaminerServiceImpl(List<QuestionService> questionServices, Random random) {
        this.questionServices = questionServices;
        this.random = random;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> result = new HashSet<>();
        int allQuestionsAmount = questionServices.stream()
                .mapToInt((e) -> e.getAll().size())
                .sum();
        if (amount > allQuestionsAmount || amount < 1) {
            throw new NotCorrectAmountQuestionsGetFromRepositoryException("Некорректное количество вопросов");
        }
        while (result.size() < amount) {
            result.add(questionServices.get(random.nextInt(questionServices.size())).getRandomQuestion());
        }
        return result;
    }
}
