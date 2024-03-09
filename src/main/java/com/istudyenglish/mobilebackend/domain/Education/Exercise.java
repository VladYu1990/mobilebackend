package com.istudyenglish.mobilebackend.domain.Education;

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
     * направление значение-перевод
     */
    private TypesOfDirectionsTranslations typeOfDirectionsTranslations;

    public void setValue(String value) {
        this.value = value;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public void setTypeOfDirectionsTranslations(TypesOfDirectionsTranslations typeOfDirectionsTranslations) {
        this.typeOfDirectionsTranslations = typeOfDirectionsTranslations;
    }
}