package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface TaskUseCases {
    public List<Task> getNextOnlyStudy(UUID userUUID,int count);
    public Task getByUserAndExercise(UUID userUUID,UUID exerciseUUID);
    public Task getOnUUID(UUID userUUID, UUID taskUUID) throws CustomException;
    public void giveAnswer(UUID userUUID, UUID taskUUID, UUID answerUUID, Instant timeAnswer) throws CustomException;
    public void create(List<UUID> exerciseUUIDList,UUID userUUID);
    public void updateFromAnswer(UUID userUUID,UUID exerciseUUID,boolean isTrue,Instant instantAnswer);
}

