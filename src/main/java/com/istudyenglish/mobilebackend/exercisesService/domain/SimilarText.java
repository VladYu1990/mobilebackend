package com.istudyenglish.mobilebackend.exercisesService.domain;

import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SimilarText {
    private Answer answer;
    private Answer similarAnswer;
    private double similarityCoefficient;

    public SimilarText(Answer answer, Answer similarAnswer) {
        this.answer = answer;
        this.similarAnswer = similarAnswer;
    }

    public void setSimilarityCoefficient(double similarityCoefficient) {
        this.similarityCoefficient = similarityCoefficient;
    }
}
