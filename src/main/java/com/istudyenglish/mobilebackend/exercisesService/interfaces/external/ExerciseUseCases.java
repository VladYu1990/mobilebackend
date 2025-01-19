package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.dictionary.domain.Source;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;


import java.util.List;
import java.util.UUID;

public interface ExerciseUseCases {
    public List<Exercise> getOnUUIDs(List<UUID> uuidList);
    public Exercise getOnUUID(UUID exerciseUUID);
    public void create(Source source);
    public boolean checkAnswer(Exercise exercise, Answer answer);
    public void save(Exercise exercise);
    public List<Exercise> getAll();
}
