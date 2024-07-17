package com.agorohov.skyprocw2examservice.controller;

import com.agorohov.skyprocw2examservice.exception.UnsupportedExamServiceOperationException;
import com.agorohov.skyprocw2examservice.model.Question;
import com.agorohov.skyprocw2examservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "exam/math")
public class MathQuestionController {

    private final QuestionService questionService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/add")
    public Question addQuestion(@RequestParam String questionText, @RequestParam String questionAnswer) {
        throwUnsupportedExamServiceOperationException();
        return questionService.add(questionText, questionAnswer);
    }

    @GetMapping(value = "/remove")
    public Question removeQuestion(@RequestParam String questionText, @RequestParam String questionAnswer) {
        throwUnsupportedExamServiceOperationException();
        return questionService.remove(new Question(questionText, questionAnswer));
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        throwUnsupportedExamServiceOperationException();
        return questionService.getAll();
    }

    @GetMapping(value = "/question")
    public Question generateQuestion() {
        return questionService.getRandomQuestion();
    }

    private void throwUnsupportedExamServiceOperationException() {
        throw new UnsupportedExamServiceOperationException("Метод не поддерживается");
    }
}
