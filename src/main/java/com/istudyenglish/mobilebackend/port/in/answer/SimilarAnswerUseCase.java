package com.istudyenglish.mobilebackend.port.in.answer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.SimilarAnswer;

import java.util.Collection;

public interface SimilarAnswerUseCase {

    Collection<SimilarAnswer> get(Answer answer, int count);
    void create(Answer answer, int count);
    void update();
    void delete();
}
