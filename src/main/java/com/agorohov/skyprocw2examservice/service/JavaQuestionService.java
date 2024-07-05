package com.agorohov.skyprocw2examservice.service;

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
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questionRepository.addQuestion(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questionRepository.removeQuestion(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getRepository();
    }

    @Override
    public Question getRandomQuestion() {
        return questionRepository.getQuestion(random.nextInt(questionRepository.getSize()));
    }
}
