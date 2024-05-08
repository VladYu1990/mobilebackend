package com.istudyenglish.mobilebackend.domain.Education;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Synchronized;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
/**
 * Класс для разделения одного обучающегося от другого
 */
public class Student {
    /**
     * Уникальный системный код студента
     */
    private UUID uuid;
    /**
     * Открытый код студента
     */
    private String code;
    /**
     * Уникальный user для студента
     */
    private UUID userUuid;
    /**
     * ФИО студента
     */
    private String studentFullName;
    /**
     * Родной язык студента
     */
    private Languages nativeLanguages;
    /**
     * Изучаемый студентом язык
     */
    private Languages languagesForStudy;
    /**
     * Дата создания студента
     */
    private Instant dateCreated;
    /**
     * Дата создания студента
     */
    private int countBoughtExercise;



    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public boolean checkUser(UUID uuid) {
        if(this.userUuid.equals(uuid)){
            return true;
        }
        else {
            return false;
        }
    }

    @Synchronized
    public void increaseToCountBoughtExercise(int count){
        this.countBoughtExercise = this.countBoughtExercise + count;
    }

    @Synchronized
    public void decreaseByCountBoughtExercise(int count){
        this.countBoughtExercise= this.countBoughtExercise - count;
        //студент не может брать в долг и не может быть нам должен
        if(this.countBoughtExercise<0){this.countBoughtExercise=0;}
    }
}
