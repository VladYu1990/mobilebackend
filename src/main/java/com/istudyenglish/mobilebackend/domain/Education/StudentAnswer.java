package com.istudyenglish.mobilebackend.domain.Education;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Builder
public class StudentAnswer {
    /**
     * UUID задания
     */
    public UUID taskUUID;
    /**
     * UUID ответа
     */
    public UUID answerUUID;
    /**
     * Время ответа студента
     */
    public Instant answerTime;
    /**
     * Правильность ответа
     */
    public boolean correct;

}
