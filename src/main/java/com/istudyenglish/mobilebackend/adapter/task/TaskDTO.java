package com.istudyenglish.mobilebackend.adapter.task;

import com.istudyenglish.mobilebackend.adapter.answer.AnswerDTO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TaskDTO{

    /**
     * Уникальный код
     */
    private String code;
    /**
     * Список ответов
     */
    private Collection<AnswerDTO> answers = new ArrayList<AnswerDTO>();
    /**
     * Вопрос
     */
    private String question;

}
