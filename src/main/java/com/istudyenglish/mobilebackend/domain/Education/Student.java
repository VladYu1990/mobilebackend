package com.istudyenglish.mobilebackend.domain.Education;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
}
