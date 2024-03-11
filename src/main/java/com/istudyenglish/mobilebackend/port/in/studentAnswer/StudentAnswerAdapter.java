package com.istudyenglish.mobilebackend.port.in.studentAnswer;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;

import java.time.Instant;

public interface StudentAnswerAdapter {
    void returnAnswer(String studentStrUUID, String taskStrUUID, String answerStrUUID, Instant time) throws CustomException;
}
