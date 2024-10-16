package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise;

import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseDBPort {

    public List<Exercise> genOnUUIDs(List<UUID> exerciseUUIDsList);
    public void create(Exercise exercise);
    public void update(Exercise exercise);

    public List<Exercise> genOnSourceUUID(UUID sourceUUID);
}
