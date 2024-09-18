package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.exercisesService.adapters.ExerciseForView;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ExerciseForViewUseCases {
    List<ExerciseForView> getNextList(UUID userUUID, int count);
}