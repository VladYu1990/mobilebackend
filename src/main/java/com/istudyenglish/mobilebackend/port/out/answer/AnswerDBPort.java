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


    void saveAnswer(Student student, Task task, Answer answer, Instant answerTime);

    Answer getByCode(String answerCode);
}
