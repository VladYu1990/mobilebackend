package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AnswerUseCasesImp implements AnswerUseCases {


    @Override
    public Answer getByUUID(UUID answerUUID) {
        return null;
    }

    @Override
    public List<Answer> getAnswerWithSimilarByUUID(UUID answerUUID) {
        return List.of();
    }
}
