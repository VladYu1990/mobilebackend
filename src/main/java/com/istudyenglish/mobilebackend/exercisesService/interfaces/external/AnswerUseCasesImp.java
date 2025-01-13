package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.configuration.Configuration;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.answer.AnswerDAO;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.answer.AnswerDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AnswerUseCasesImp implements AnswerUseCases {
    private AnswerDBPort answerDBPort;

    @Autowired
    public AnswerUseCasesImp(AnswerDAO answerDBPort) {
        this.answerDBPort = answerDBPort;
    }

    @Override
    public Answer getByUUID(UUID answerUUID) {
        return answerDBPort.get(answerUUID);
    }

    @Override
    public Answer getByValue(String value) {
        return answerDBPort.get(value);
    }
}
