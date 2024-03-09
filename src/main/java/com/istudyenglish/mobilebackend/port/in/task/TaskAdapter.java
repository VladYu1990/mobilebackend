package com.istudyenglish.mobilebackend.port.in.task;

import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import java.time.Instant;
import java.util.Collection;

public interface TaskAdapter {
    void create(String token, String studentCode, String exerciseUUID, Instant time) throws Exception;

    Collection<Task> getNextTask(String token, String studentCode, int amount, Instant time) throws Exception;

    boolean checkAnswer(String token, String studentCode, String taskCode, String answerCode) throws Exception;

    void returnAnswer(String token, String studentCode, String taskCode, String answerCode, Instant time) throws Exception;

}
