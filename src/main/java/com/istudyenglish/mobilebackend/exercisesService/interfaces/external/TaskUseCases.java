package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;


import com.istudyenglish.mobilebackend.CustomException;
import com.istudyenglish.mobilebackend.exercisesService.domain.task.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.userService.domain.User;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface TaskUseCases {
    public List<Task> getNextOnlyStudy(User user, int maxCountTasks);
    public Task getByUserAndExercise(UUID userUUID,UUID exerciseUUID);
    public Task getOnUUID(UUID userUUID, UUID taskUUID) throws CustomException;
    public void giveAnswer(UUID userUUID, UUID taskUUID, UUID answerUUID, Instant timeAnswer) throws CustomException;
    public void create(List<Exercise> exerciseList, User user);
    public void create(Exercise exercise, User user);
    public void updateFromAnswer(UUID userUUID,UUID exerciseUUID,boolean isTrue,Instant instantAnswer);
}

