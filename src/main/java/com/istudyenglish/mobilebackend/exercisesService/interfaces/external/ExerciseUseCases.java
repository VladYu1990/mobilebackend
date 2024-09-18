package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseUseCases {
    public List<Exercise> getOnUUIDs(List<UUID> uuidList);
    public Exercise getOnUUID(UUID exerciseUUID);
    public void create(UUID wordUUID);
    public boolean checkAnswer(UUID exerciseUUID,UUID answerUUID);

    public void addForUser(UUID sourceUUID,UUID userUUID);
}
