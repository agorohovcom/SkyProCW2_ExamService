package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.exception.ParamIsNotPresentException;
import com.agorohov.skyprocw2examservice.exception.QuestionInNotExistException;
import com.agorohov.skyprocw2examservice.exception.QuestionIsAlreadyExistException;
import com.agorohov.skyprocw2examservice.model.Question;
import com.agorohov.skyprocw2examservice.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final Random random;

    public JavaQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.random = new Random();
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
        if (questionRepository.isQuestionPresent(question)) {
            throw new QuestionIsAlreadyExistException("Такой вопрос уже добавлен, повторное добавление невозможно");
        }
        questionRepository.addQuestion(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        checkParamIsPresent(question);
        if (!questionRepository.isQuestionPresent(question)) {
            throw new QuestionInNotExistException("Такого вопроса нет, удаление невозможно");
        }
        questionRepository.removeQuestion(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getRepository();
    }

    @Override
    public Question getRandomQuestion() {
        return questionRepository.getQuestion(
                random.nextInt(questionRepository.getSize()));
    }

    private void checkParamIsPresent(Object o) {
        if (o == null || o.toString().isBlank()) {
            throw new ParamIsNotPresentException("Некорректное значение параметра");
        }
    }
}