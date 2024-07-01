package com.agorohov.skyprocw2examservice.controller;

import com.agorohov.skyprocw2examservice.model.Question;
import com.agorohov.skyprocw2examservice.service.ExaminerService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    public Collection<Question> getQuestions(int amount) {
        return examinerService.getQuestions(amount);
    }
}
