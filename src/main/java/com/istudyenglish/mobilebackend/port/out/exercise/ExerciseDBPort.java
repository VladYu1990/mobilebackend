package com.istudyenglish.mobilebackend.port.out.exercise;

import com.istudyenglish.mobilebackend.domain.Education.Exercise;

import java.util.UUID;

public interface ExerciseDBPort {

    Exercise get(UUID uuid);
    void save(Exercise exercise);
    void update(Exercise exercise);
    void delete(Exercise exercise);
}
