package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.SimilarAnswer;

import java.util.List;
import java.util.UUID;

public interface SimilarAnswerUseCases {
    public List<Answer> getSimilarByAnswerUUID(Answer answer, int maxCountSimilarAnswer);
    public void create(UUID answer,int count);
    public void countUpForAnswer(UUID answerUUID);
    public void updateAll();
    public void update(Answer answer);
}
