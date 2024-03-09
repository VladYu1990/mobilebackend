package com.istudyenglish.mobilebackend.port.in.exercise;

import com.istudyenglish.mobilebackend.domain.Education.Exercise;
import com.istudyenglish.mobilebackend.domain.dictionary.Source;

import java.util.UUID;

public interface ExerciseUseCase {

    Exercise get(UUID uuid);
    Exercise get(String code);
    void create(Source source);
    void delete(Exercise exercise);
}
