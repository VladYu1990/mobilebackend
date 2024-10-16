package com.istudyenglish.mobilebackend.exercisesService.domain.exercise;


import com.istudyenglish.mobilebackend.exercisesService.domain.Question;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
/**
 * Упражнение, общее для всех обучающихся
 */
public class Exercise {

    /**
     * Уникальный идентификатор упражнения
     */
    private UUID uuid;
    /**
     * Тип упражнения
     */
    private TypesOfExercise typesOfExercise;
    /**
     * Тип источника для упражнения
     */
    private UUID sourceUUID;
    /**
     * Вопрос
     */
    @Setter
    private Question question;
    /**
     * Ответ
     */
    @Setter
    private Answer answer;


    public boolean checkAnswer(UUID answerUUID){
        return this.answer.getUuid().equals(answerUUID);
    }
}