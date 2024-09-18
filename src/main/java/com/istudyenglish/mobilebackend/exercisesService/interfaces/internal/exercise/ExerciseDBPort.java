package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise;

import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;

import java.util.UUID;

public interface ExerciseDBPort {

     public Exercise genOnUUID(UUID exerciseUUID);
    public void create(Exercise exercise);
    public void update(Exercise exercise);

}
