package com.istudyenglish.mobilebackend.port.out.exercise;

import com.istudyenglish.mobilebackend.tasksService.domain.exercise.Exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseDBPort {

    Exercise get(UUID uuid);
    List<Exercise> get(List<String> strings);
    List<Exercise> getFromWordUUID(List<String> collection);
    void save(Exercise exercise);
    void update(Exercise exercise);
    void delete(Exercise exercise);


}
