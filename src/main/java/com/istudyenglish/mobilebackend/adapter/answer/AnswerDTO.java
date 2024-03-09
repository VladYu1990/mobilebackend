package com.istudyenglish.mobilebackend.adapter.answer;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDTO {
    /**
     * Код ответа
     */
    String code;
    /**
     * Текстовое значение ответа
     */
    String value;
    /**
     * Ссылка на звуковой файл ответа
     */
    String soundUrl;
    /**
     * Признак корректности ответа
     */
    Boolean correct;
}
