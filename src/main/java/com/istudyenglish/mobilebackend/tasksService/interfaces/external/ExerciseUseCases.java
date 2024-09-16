package com.istudyenglish.mobilebackend.tasksService.interfaces.external;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.tasksService.domain.exercise.Exercise;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ExerciseUseCases {
    public List<Exercise> getOnUUIDs(List<UUID> uuidList);
    public Exercise getOnUUID(UUID exerciseUUID);
    public void create(UUID wordUUID);
    public boolean checkAnswer(UUID exerciseUUID,UUID answerUUID);
}
