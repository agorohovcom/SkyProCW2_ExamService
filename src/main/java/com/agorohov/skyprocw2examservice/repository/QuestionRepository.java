package com.agorohov.skyprocw2examservice.repository;

import com.agorohov.skyprocw2examservice.model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class QuestionRepository {

    private Set<Question> repository;

    @PostConstruct
    private void init() {
        repository = new HashSet<>();
        repository.add(new Question("Вопрос 1", "Ответ 1"));
        repository.add(new Question("Вопрос 2", "Ответ 2"));
        repository.add(new Question("Вопрос 3", "Ответ 3"));
        repository.add(new Question("Вопрос 4", "Ответ 4"));
        repository.add(new Question("Вопрос 5", "Ответ 5"));
    }

    public Collection<Question> getRepository() {
        return List.copyOf(repository);
    }

    public int getSize() {
        return repository.size();
    }

    public Question getQuestion(int index) {
        return List.copyOf(repository).get(index);
    }

    public void addQuestion(Question question) {
        repository.add(question);
    }

    public boolean removeQuestion(Question question) {
        return repository.remove(question);
    }
}
