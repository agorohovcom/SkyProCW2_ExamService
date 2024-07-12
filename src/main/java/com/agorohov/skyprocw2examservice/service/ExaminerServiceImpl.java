package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.NotCorrectAmountQuestionsGetFromRepositoryException;
import com.agorohov.skyprocw2examservice.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> questionServices;

    private final Random random = new Random();

    public ExaminerServiceImpl(List<QuestionService> questionServices) {
        this.questionServices = questionServices;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        // Получаем список непустых сервисов
        List<QuestionService> servicesWithQuestions = questionServices.stream()
                .filter(Objects::nonNull)
                .filter((e) -> !e.getAll().isEmpty())
                .collect(Collectors.toCollection(ArrayList::new));
        // Получаем общее кол-во вопросов из всех сервисов
        int totalQuestionsAmount = servicesWithQuestions.stream()
                .filter(Objects::nonNull)
                .mapToInt((e) -> e.getAll().size())
                .sum();
        // Проверяем, в допустимых ли пределах amount
        if (amount > totalQuestionsAmount || amount < 1) {
            throw new NotCorrectAmountQuestionsGetFromRepositoryException("Некорректное количество вопросов");
        }
        // Инициализируем множество, куда будем складывать уникальные рандомные вопросы
        Set<Question> result = new HashSet<>();
        // Цикл получения случайных вопросов
        while (result.size() < amount) {
            // Получаем случайный вопрос и добавляем в множество
            result.add(servicesWithQuestions.get(random.nextInt(servicesWithQuestions.size())).getRandomQuestion());
        }
        return result;
    }
}
