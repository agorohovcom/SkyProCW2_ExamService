package com.agorohov.skyprocw2examservice.repository;

import com.agorohov.skyprocw2examservice.exception.QuestionWasNotAddedException;
import com.agorohov.skyprocw2examservice.exception.QuestionWasNotRemovedException;
import com.agorohov.skyprocw2examservice.model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Qualifier("mathQuestionRepository")
public class MathQuestionRepository implements QuestionRepository {

    private Set<Question> repository;

    @PostConstruct
    private void init() {
        repository = new HashSet<>();
        repository.add(new Question("Вопрос по Math 1", "Ответ по Math 1"));
        repository.add(new Question("Вопрос по Math 2", "Ответ по Math 2"));
        repository.add(new Question("Вопрос по Math 3", "Ответ по Math 3"));
        repository.add(new Question("Вопрос по Math 4", "Ответ по Math 4"));
        repository.add(new Question("Вопрос по Math 5", "Ответ по Math 5"));
    }

    public Collection<Question> getAll() {
        return List.copyOf(repository);
    }

    public Question add(Question question) {
        if (repository.add(question)) {
            return question;
        } else {
            throw new QuestionWasNotAddedException("Не удалось добавить вопрос");
        }
    }

    public Question remove(Question question) {
        if (repository.remove(question)) {
            return question;
        } else {
            throw new QuestionWasNotRemovedException("Не удалось удалить вопрос");
        }
    }
}
