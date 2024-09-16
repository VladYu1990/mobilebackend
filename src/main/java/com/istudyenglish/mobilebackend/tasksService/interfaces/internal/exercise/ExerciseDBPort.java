package com.istudyenglish.mobilebackend.tasksService.interfaces.internal.exercise;

import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.tasksService.domain.exercise.Exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseDBPort {

     public Exercise genOnUUID(UUID exerciseUUID);
    public void create(Exercise exercise);
    public void update(Exercise exercise);

}
