package com.istudyenglish.mobilebackend.port.out.similarAnswer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.SimilarAnswer;

import java.util.Collection;

public interface SimilarAnswersDBport {


    Collection<SimilarAnswer> getSimilar(Answer answer, int count);
    void saveSimilar(Collection<SimilarAnswer> similarAnswerCollection);
    void update(Collection<SimilarAnswer> similarAnswerCollection);
}