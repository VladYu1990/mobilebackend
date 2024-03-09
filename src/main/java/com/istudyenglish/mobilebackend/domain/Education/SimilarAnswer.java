package com.istudyenglish.mobilebackend.domain.Education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class SimilarAnswer implements Comparable<SimilarAnswer>{
    /**
     * Код верного ответа
     */
    UUID answerTrue;
    /**
     * Код похожего ответа
     */
    UUID answerSimilarUUID;
    /**
     * Вес похожести
     */
    double similarityWeight;

    @Override
    public int compareTo(SimilarAnswer similarAnswer) {
        if (this.similarityWeight >= similarAnswer.similarityWeight) {
            return 1;
        } else {
            return -1;
        }
    }
}
