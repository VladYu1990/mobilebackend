package com.istudyenglish.mobilebackend.port.in.exercise;

import com.istudyenglish.mobilebackend.domain.Education.Exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseUseCase {

    Exercise get(UUID uuid);
    List<Exercise> get(List<String> collection);
    List<Exercise> getFromWordUUID(List<String> collection);
    void create(Source source);
    void delete(Exercise exercise);
}
