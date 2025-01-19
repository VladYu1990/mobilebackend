package com.istudyenglish.mobilebackend.exercisesService.adapters;

import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.SimilarAnswerUseCases;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.SimilarAnswerUseCasesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AnswerForViewBuilder {
    private SimilarAnswerUseCases similarAnswerUseCases;

    @Autowired
public AnswerForViewBuilder(SimilarAnswerUseCasesImp similarAnswerUseCases) {
        this.similarAnswerUseCases = similarAnswerUseCases;
    }

    public List<AnswerForView> build(Answer answerTrue, int maxCountAnswers){
        maxCountAnswers = maxCountAnswers - 1;
        if(maxCountAnswers<0){
            maxCountAnswers = 0;
        }
        return adapt(answerTrue, similarAnswerUseCases.getSimilarByAnswerUUID(answerTrue,maxCountAnswers));
    }


    public List<AnswerForView> adapt(Answer answerTrue, List<Answer> similarAnswerList){
        List<AnswerForView> list = new ArrayList<>();
        for(Answer answer:similarAnswerList){
            list.add(new AnswerForView(
                    answer.getUuid().toString(),
                    answer.getValue(),
                    answer.getLanguage().toString(),
                    false));
        }
        list.add(new AnswerForView(
                answerTrue.getUuid().toString(),
                answerTrue.getValue(),
                answerTrue.getLanguage().toString(),
                true));

        Collections.shuffle(list);
        return list;
    }

    public List<AnswerForView> buildForEmpty(int maxCountAnswer) {
        List<AnswerForView> answerForViewList = new ArrayList<>();
        for(int i=0;i<maxCountAnswer;i++){
            answerForViewList.add(new AnswerForView(maxCountAnswer));
        }
        return answerForViewList;
    }
}
