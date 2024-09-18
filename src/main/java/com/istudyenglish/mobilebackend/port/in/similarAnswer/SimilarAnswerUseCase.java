package com.istudyenglish.mobilebackend.port.in.similarAnswer;

import java.util.Collection;

public interface SimilarAnswerUseCase {

    Collection<SimilarAnswer> get(Answer answer, int count);
    void create(Answer answer, int count);
    void update();
    void delete();
}
