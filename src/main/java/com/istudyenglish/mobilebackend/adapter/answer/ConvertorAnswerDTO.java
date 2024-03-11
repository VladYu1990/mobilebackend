package com.istudyenglish.mobilebackend.adapter.answer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.SimilarAnswer;
import com.istudyenglish.mobilebackend.port.in.answer.AnswerUseCase;
import com.istudyenglish.mobilebackend.port.in.similarAnswer.SimilarAnswerUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ConvertorAnswerDTO {

    AnswerUseCase answerUseCase;
    SimilarAnswerUseCase similarAnswerUseCase;

    @Autowired
    public ConvertorAnswerDTO(AnswerUseCase answerUseCase, SimilarAnswerUseCase similarAnswerUseCase) {
        this.answerUseCase = answerUseCase;
        this.similarAnswerUseCase = similarAnswerUseCase;
    }

    public AnswerDTO convert(Answer answer, boolean correct) {

        return AnswerDTO.builder().
                code(answer.getCode()).
                value(answer.getValue()).
                soundUrl(answer.getSoundURL()).
                correct(correct).
                build();
    }
    public Collection<AnswerDTO> convert(Answer answer, int count) {

        ArrayList<AnswerDTO> answerDTOCollection = new ArrayList<AnswerDTO>();

        answerDTOCollection.add(convert(answer, true));

        for (SimilarAnswer i : similarAnswerUseCase.get(answer, count - 1)) {
            Answer answer1 = answerUseCase.getByUUID(i.getAnswerSimilarUUID());
            answerDTOCollection.add(
                    convert(answer1, false));
        }

        if (answerDTOCollection.size() < count) {
            int random = (int) Math.random() * answerDTOCollection.size();
            answerDTOCollection.add(
                    answerDTOCollection.get(random));
        }

        return answerDTOCollection;
    }
}
