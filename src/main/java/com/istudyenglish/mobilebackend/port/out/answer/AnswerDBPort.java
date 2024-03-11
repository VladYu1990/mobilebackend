package com.istudyenglish.mobilebackend.port.out.answer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

public interface AnswerDBPort {

    Answer getByUUID(UUID uuid);
    Answer getByString(String value);

    void update(Collection<Task> taskCollection, Instant instant);

    void delete(Collection<Task> taskCollection, Instant instant);

//todo вынести такой тип овтета в отдельный объект/отдельное фло/отдельную таблицу
    void saveAnswer(Task task, Answer answer, Instant answerTime,boolean isTrue);

    Answer getByCode(String answerCode);
}
