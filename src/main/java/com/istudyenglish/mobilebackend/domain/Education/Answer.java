package com.istudyenglish.mobilebackend.domain.Education;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Answer {
    /**
     * Уникальный идентификатор ответа
     */
    UUID uuid;
    /**
     * Код ответа
     */
    String code;
    /**
     * Текстовое представление ответа
     */
    String value;
    /**
     * Ссылка на звуковой файл ответа
     */
    String soundURL;


}
