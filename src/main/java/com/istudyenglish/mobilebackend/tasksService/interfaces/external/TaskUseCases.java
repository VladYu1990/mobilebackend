package com.istudyenglish.mobilebackend.tasksService.interfaces.external;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface TaskUseCases {
    public List<Task> getNext(UUID userUUID,int count);
    public Task getOnUUID(UUID userUUID, UUID taskUUID) throws CustomException;
    public void giveAnswer(UUID userUUID, UUID taskUUID, UUID answerUUID, Instant timeAnswer) throws CustomException;
    public void create(UUID userUUID,UUID exerciseUUID);
    public void checkTaskBelongsUser(UUID userUUID, Task task) throws CustomException;
}
