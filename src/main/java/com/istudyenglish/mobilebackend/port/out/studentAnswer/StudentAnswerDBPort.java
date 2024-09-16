package com.istudyenglish.mobilebackend.port.out.studentAnswer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;

import java.time.Instant;

public interface StudentAnswerDBPort {


    void saveAnswer(Task task, Answer answer, Instant answerTime,boolean isTrue);
}
