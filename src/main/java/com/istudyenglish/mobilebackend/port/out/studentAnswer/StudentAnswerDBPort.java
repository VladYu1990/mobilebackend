package com.istudyenglish.mobilebackend.port.out.studentAnswer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;

import java.time.Instant;

public interface StudentAnswerDBPort {


    void saveAnswer(Task task, Answer answer, Instant answerTime,boolean isTrue);
}
