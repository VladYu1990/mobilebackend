package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.exercisesService.domain.SimilarAnswer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SimilarAnswerUseCasesImp implements SimilarAnswerUseCases {

    @Override
    public List<SimilarAnswer> getSimilarByAnswerUUID(UUID answerUUID) {
        return List.of();
    }


    @Override
    public void create(UUID answer, int count) {

    }

    @Override
    public void countUpForAnswer(UUID answerUUID) {

    }
}
