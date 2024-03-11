package com.istudyenglish.mobilebackend.port.in.task;

import com.istudyenglish.mobilebackend.adapter.task.TaskDTO;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import java.time.Instant;
import java.util.Collection;

public interface TaskAdapter {
    void create(String studentCode, String exerciseUUID, Instant time) throws CustomException;

    Collection<TaskDTO> getNextTask(String studentCode, int amountTask,int amountAnswers, Instant time) throws CustomException;

}
