package com.istudyenglish.mobilebackend.domain.Education;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Тип упражнения
 */
public enum TypesOfExercise {
    /**
     * Задание на изучение написания
     */
    WRITING,
    /**
     * Задание на изучение чтения/понимания
     */
    READING,
    /**
     * Задание на правильное произношение
     */
    SPEAKING,
    /**
     * Задание на умение понять на слух
     */
    HEARING;

    public static Collection<TypesOfExercise> getList(){
        Collection<TypesOfExercise> typesOfExercises = new ArrayList<TypesOfExercise>();
        typesOfExercises.add(WRITING);
        typesOfExercises.add(READING);
        typesOfExercises.add(SPEAKING);
        typesOfExercises.add(HEARING);

        return typesOfExercises;

    }
}
