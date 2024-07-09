package com.agorohov.skyprocw2examservice.repository;

import com.agorohov.skyprocw2examservice.exception.QuestionWasNotAddedException;
import com.agorohov.skyprocw2examservice.exception.QuestionWasNotRemovedException;
import com.agorohov.skyprocw2examservice.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.agorohov.skyprocw2examservice.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionRepositoryTest {

    private JavaQuestionRepository out;

    @BeforeEach
    void setUp() {
        out = new JavaQuestionRepository();
        for (Question q : ALL_QUESTIONS) {
            out.add(q);
        }
    }

    @Test
    void repositoryIsNotNull() {
        assertNotNull(out.getAll());
    }

    @Test
    void shouldGetAllCorrectly() {
        Set<Question> actual = Set.copyOf(out.getAll());
        assertEquals(ALL_QUESTIONS, actual);
    }

    @Test
    void shouldAddCorrectly() {
        assertThrows(QuestionWasNotAddedException.class,
                () -> out.add(SAME_QUESTION));

        int repositorySize = out.getAll().size();
        assertEquals(NO_SUCH_QUESTION, out.add(NO_SUCH_QUESTION));
        assertEquals(repositorySize + 1, out.getAll().size());
    }

    @Test
    void shouldRemoveCorrectly() {
        assertThrows(QuestionWasNotRemovedException.class,
                () -> out.remove(NO_SUCH_QUESTION));

        int repositorySize = out.getAll().size();
        assertEquals(QUESTION_1, out.remove(QUESTION_1));
        assertEquals(repositorySize - 1, out.getAll().size());
    }
}