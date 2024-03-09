package com.istudyenglish.mobilebackend.application.answer;

import com.istudyenglish.mobilebackend.adapter.answer.SimilarAnswersDAO;
import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.SimilarAnswer;
import com.istudyenglish.mobilebackend.port.in.answer.SimilarAnswerUseCase;
import com.istudyenglish.mobilebackend.port.out.answer.SimilarAnswersDBport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SimilarAnswerUseCaseImpl implements SimilarAnswerUseCase{

    SimilarAnswersDBport similarAnswersDBport;

    @Autowired
    public SimilarAnswerUseCaseImpl(SimilarAnswersDAO similarAnswersDBport) {
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
