package com.agorohov.skyprocw2examservice.constants;

import com.agorohov.skyprocw2examservice.model.Question;

import java.util.Collection;
import java.util.Set;

public class Constants {
    public static String QUESTION_TEXT = "Soma question";
    public static String QUESTION_ANSWER = "Some answer";
    public static String EMPTY_STRING = "";

    public static final Question QUESTION_1 = new Question("New question 1 text", "New question 1 answer");
    public static final Question QUESTION_2 = new Question("New question 2 text", "New question 2 answer");
    public static final Question QUESTION_3 = new Question("New question 3 text", "New question 3 answer");

    public static final Question SAME_QUESTION = QUESTION_1;
    public static final Question NO_SUCH_QUESTION = new Question("Вопрос отсутствует", "Ответ на отсутствующий вопрос");

    public static final Collection<Question> ALL_QUESTIONS = Set.of(QUESTION_1, QUESTION_2, QUESTION_3);
    public static final Collection<Question> EMPTY_QUESTIONS_COLLECTION = Set.of();
}
