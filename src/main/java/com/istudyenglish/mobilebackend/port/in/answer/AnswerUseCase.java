package com.istudyenglish.mobilebackend.port.in.answer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;

import java.time.Instant;
import java.util.UUID;

public interface AnswerUseCase {

    Answer getByUUID(UUID uuid);
    Answer getByString(String value);
    void create();
    void update();
    void delete();

    void saveAnswer(Student student, Task task, Answer answer, Instant answerTime);

    Answer getByCode(String answerCode);
}
