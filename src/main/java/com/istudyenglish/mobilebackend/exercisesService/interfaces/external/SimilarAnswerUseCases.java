package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.exercisesService.domain.SimilarText;

import java.util.List;
import java.util.UUID;

public interface SimilarAnswerUseCases {
    public List<SimilarText> getSimilarByAnswerUUID(UUID answerUUID);
    public void create(UUID answer,int count);
    public void countUpForAnswer(UUID answerUUID);
}
