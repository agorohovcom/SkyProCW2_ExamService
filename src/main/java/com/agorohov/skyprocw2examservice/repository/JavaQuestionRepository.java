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
@Qualifier("javaQuestionRepository")
public class JavaQuestionRepository implements QuestionRepository {

    private final Set<Question> repository;

    public JavaQuestionRepository() {
        repository = new HashSet<>();
    }

    @PostConstruct
    private void init() {
        repository.add(new Question("Вопрос по Java 1", "Ответ по Java 1"));
        repository.add(new Question("Вопрос по Java 2", "Ответ по Java 2"));
        repository.add(new Question("Вопрос по Java 3", "Ответ по Java 3"));
        repository.add(new Question("Вопрос по Java 4", "Ответ по Java 4"));
        repository.add(new Question("Вопрос по Java 5", "Ответ по Java 5"));
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
