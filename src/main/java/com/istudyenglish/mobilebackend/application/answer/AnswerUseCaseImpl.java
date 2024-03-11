package com.istudyenglish.mobilebackend.application.answer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.adapter.answer.AnswerDAO;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.port.in.answer.AnswerUseCase;
import com.istudyenglish.mobilebackend.port.out.answer.AnswerDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class AnswerUseCaseImpl implements AnswerUseCase {

    AnswerDBPort answerDBPort;

    @Autowired
    public AnswerUseCaseImpl(AnswerDAO answerDBPort) {
        this.answerDBPort = answerDBPort;
    }

    public Answer getByUUID(UUID uuid) {
        return answerDBPort.getByUUID(uuid);
    }

    public Answer getByString(String value){

        return answerDBPort.getByString(value);
    }

    public void create() {

    }

    public void update() {

    }

    public void delete() {

    }

    public void saveAnswer(Task task, Answer answer, Instant answerTime,boolean isTrue) {
        answerDBPort.saveAnswer(task,answer,answerTime,isTrue);
    }


    public Answer getByCode(String answerCode) {
        return answerDBPort.getByCode(answerCode);
    }
}
