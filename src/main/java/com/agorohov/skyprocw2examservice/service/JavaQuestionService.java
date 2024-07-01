package com.agorohov.skyprocw2examservice.service;

import com.agorohov.skyprocw2examservice.model.Question;
import com.agorohov.skyprocw2examservice.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;

    public JavaQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question result = new Question(question, answer);
        questionRepository.addQuestion(result);
        return result;
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
        return questionRepository.getQuestion(new Random().nextInt(questionRepository.getSize()));
    }
}
