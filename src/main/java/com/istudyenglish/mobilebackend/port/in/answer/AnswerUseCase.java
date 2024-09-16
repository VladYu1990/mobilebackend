package com.istudyenglish.mobilebackend.port.in.answer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;

import java.time.Instant;
import java.util.UUID;

public interface AnswerUseCase {

    Answer getByUUID(UUID uuid);
    Answer getByString(String value);
    void create();
    void update();
    void delete();

    void saveAnswer(Task task, Answer answer, Instant answerTime,boolean isTrue);

    Answer getByCode(String answerCode);
}
