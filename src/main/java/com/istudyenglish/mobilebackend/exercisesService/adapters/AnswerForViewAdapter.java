package com.istudyenglish.mobilebackend.exercisesService.adapters;

import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AnswerForViewAdapter {

    public List<AnswerForView> adapt(List<Answer> answerList, UUID uuidTrueAnswer){
        List<AnswerForView> list = new ArrayList<>();
        for(Answer answer:answerList){
            boolean isTrue = false;
            if(answer.getUuid().equals(uuidTrueAnswer)){
                isTrue = true;
            }
            AnswerForView answerForView = new AnswerForView(
                    answer.getUuid().toString(),
                    answer.getValue(),
                    answer.getLanguage(),
                    isTrue);
            list.add(answerForView);
        }

        return list;
    }
}
