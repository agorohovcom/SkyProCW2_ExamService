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
        // Получаем общее кол-во вопросов из всех сервисов.
        // Можно было бы выгрузить сразу все вопросы в список и работать с ним, но по заданию надо
        // использовать getRandomQuestion() из QuestionService
        int totalQuestionsAmount = questionServices.stream()
                .mapToInt((e) -> e.getAll().size())
                .sum();
        // Проверяем, в допустимых ли пределах amount
        if (amount > totalQuestionsAmount || amount < 1) {
            throw new NotCorrectAmountQuestionsGetFromRepositoryException("Некорректное количество вопросов");
        }
        // Инициализируем множество, куда будем складывать рандомные вопросы
        Set<Question> result = new HashSet<>();
        // Получаем список индексов сервисов, в которых есть хотя бы 1 вопрос
        // (чтобы не запрашивать вопрос у пустых сервисов)
        List<Integer> indexesOfServicesWithQuestions = new ArrayList<>();
        for (int i = 0; i < questionServices.size(); i++) {
            if (questionServices.get(i) != null && !questionServices.get(i).getAll().isEmpty()) {
                indexesOfServicesWithQuestions.add(i);
            }
        }
        // Цикл получения случайных вопросов
        while (result.size() < amount) {
            // Получаем индекс сервиса
            int randServiceIndex = random.nextInt(indexesOfServicesWithQuestions.size());
            // Получаем случайный вопрос и добавляем в множество
            result.add(questionServices.get(randServiceIndex).getRandomQuestion());
        }
        return result;
    }
}
