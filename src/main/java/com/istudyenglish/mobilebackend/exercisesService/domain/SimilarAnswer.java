package com.istudyenglish.mobilebackend.exercisesService.domain;

import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SimilarAnswer {
    private Answer answer;
    private Answer similarAnswer;
    private double similarityCoefficient;

    public SimilarAnswer(Answer answer, Answer similarAnswer) {
        this.answer = answer;
        this.similarAnswer = similarAnswer;
    }

    public void setSimilarityCoefficient(double similarityCoefficient) {
        this.similarityCoefficient = similarityCoefficient;
    }
}
