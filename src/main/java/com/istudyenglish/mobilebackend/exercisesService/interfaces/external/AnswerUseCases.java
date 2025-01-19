package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.dictionary.domain.Language;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;

import java.util.UUID;

public interface AnswerUseCases {

    Answer getByUUID(UUID answerUUID);
    Answer getByValue(String value,Language lang);
    void create(String answer, Language lan);
}
