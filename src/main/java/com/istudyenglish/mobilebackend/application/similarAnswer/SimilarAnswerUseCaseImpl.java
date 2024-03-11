package com.istudyenglish.mobilebackend.application.similarAnswer;

import com.istudyenglish.mobilebackend.adapter.similarAnswer.SimilarAnswerDAO;
import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.SimilarAnswer;
import com.istudyenglish.mobilebackend.port.in.similarAnswer.SimilarAnswerUseCase;
import com.istudyenglish.mobilebackend.port.out.similarAnswer.SimilarAnswersDBport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SimilarAnswerUseCaseImpl implements SimilarAnswerUseCase{

    SimilarAnswersDBport similarAnswersDBport;

    @Autowired
    public SimilarAnswerUseCaseImpl(SimilarAnswerDAO similarAnswersDBport) {
        this.similarAnswersDBport = similarAnswersDBport;
    }

    public Collection<SimilarAnswer> get(Answer answer, int count) {
        return similarAnswersDBport.getSimilar(answer,count);
    }

    public void create(Answer answer, int count) {
        //todo similarAnswersDBport.saveSimilar();

    }

    public void update() {

    }

    public void delete() {

    }
}
