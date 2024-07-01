package com.agorohov.skyprocw2examservice.controller;

import com.agorohov.skyprocw2examservice.model.Question;
import com.agorohov.skyprocw2examservice.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "exam/java/")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "add")
    public Question addQuestion(@RequestParam String questionText, @RequestParam String questionAnswer) {
        return questionService.add(questionText, questionAnswer);
    }

    @GetMapping(value = "remove")
    public Question removeQuestion(@RequestParam String questionText, @RequestParam String questionAnswer) {
        return questionService.remove(new Question(questionText, questionAnswer));
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }
}
