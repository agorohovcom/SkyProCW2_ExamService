package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.ParamIsNotPresentException;
import com.agorohov.skyprocw2examservice.exception.QuestionInNotExistException;
import com.agorohov.skyprocw2examservice.exception.QuestionIsAlreadyExistException;
import com.agorohov.skyprocw2examservice.model.Question;
import com.agorohov.skyprocw2examservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
@Qualifier("mathQuestionService")
public class MathQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final Random random;

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository questionRepository, Random random) {
        this.questionRepository = questionRepository;
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
        if (questionRepository.getAll().contains(question)) {
            throw new QuestionIsAlreadyExistException("Такой вопрос уже добавлен, повторное добавление невозможно");
        }
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        checkParamIsPresent(question);
        if (!questionRepository.getAll().contains(question)) {
            throw new QuestionInNotExistException("Такого вопроса нет, удаление невозможно");
        }
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        return List.copyOf(questionRepository.getAll())
                .get(random.nextInt(questionRepository.getAll().size()));
    }

    private void checkParamIsPresent(Object o) {
        if (o == null || o.toString().isBlank()) {
            throw new ParamIsNotPresentException("Некорректное значение параметра");
        }
    }
}
