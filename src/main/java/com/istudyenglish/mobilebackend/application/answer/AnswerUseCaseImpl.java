package com.istudyenglish.mobilebackend.application.answer;

import com.istudyenglish.mobilebackend.adapter.answer.SimilarAnswersDAO;
import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.adapter.answer.AnswerDAO;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.port.in.answer.AnswerUseCase;
import com.istudyenglish.mobilebackend.port.out.answer.AnswerDBPort;
import com.istudyenglish.mobilebackend.port.out.answer.SimilarAnswersDBport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class AnswerUseCaseImpl implements AnswerUseCase {

    AnswerDBPort answerDBPort;
    SimilarAnswersDBport similarAnswersDBport;

    @Autowired
    public AnswerUseCaseImpl(AnswerDAO answerDBPort, SimilarAnswersDAO answerSimilarDBport) {
        this.answerDBPort = answerDBPort;
        this.similarAnswersDBport = answerSimilarDBport;
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

    public void saveAnswer(Student student, Task task, Answer answer, Instant answerTime) {
        //todo записываем данные ответы юзером
        //кто, на какую таску, какой ответ дал и когда
        answerDBPort.saveAnswer(student,task,answer,answerTime);
    }


    public Answer getByCode(String answerCode) {
        return answerDBPort.getByCode(answerCode);
    }
}
