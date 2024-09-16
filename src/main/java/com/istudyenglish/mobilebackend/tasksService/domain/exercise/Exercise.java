package com.istudyenglish.mobilebackend.tasksService.domain.exercise;

import com.istudyenglish.mobilebackend.domain.Education.TypesOfDirectionsTranslations;
import com.istudyenglish.mobilebackend.domain.Education.TypesOfExercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
/**
 * Хранит упражнения, общее для всех обучающихся
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
     * Текст
     */
    private String value;
    /**
     * Перевод
     */
    private String translate;
    /**
     * Направление значение-перевод
     */
    private TypesOfDirectionsTranslations typeOfDirectionsTranslations;
    /**
     * UUID верного ответа
     */
    private UUID trueAnswerCode;


    public boolean checkAnswer(UUID answerUUID){
        return this.trueAnswerCode.equals(answerUUID);
    }
}