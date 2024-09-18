package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;

import java.util.List;
import java.util.UUID;

public interface AnswerUseCases {

    Answer getByUUID(UUID answerUUID);
    List<Answer> getAnswerWithSimilarByUUID(UUID answerUUID);
}
