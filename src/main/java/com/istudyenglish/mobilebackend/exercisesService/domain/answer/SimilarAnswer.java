package com.istudyenglish.mobilebackend.exercisesService.domain.answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@Getter
@Builder
public class SimilarAnswer implements Comparable<SimilarAnswer>{
    private Answer answer;
    private Answer similarAnswer;
    private double similarityCoefficient;

    public SimilarAnswer(Answer answer, Answer similarAnswer) {
        this.answer = answer;
        this.similarAnswer = similarAnswer;
        setSimilarityCoefficient();
    }

    public void setSimilarityCoefficient() {
        double count = this.answer.getValue().length();
        double levDis =  StringUtils.getLevenshteinDistance(answer.getValue(),similarAnswer.getValue());
        double d = (count - levDis)/count;
        this.similarityCoefficient = d;
    }

    @Override
    public int compareTo(SimilarAnswer similarAnswer1) {
        if(this.similarityCoefficient>similarAnswer1.similarityCoefficient){
            return 1;
        }
        return -1;

    }
}
