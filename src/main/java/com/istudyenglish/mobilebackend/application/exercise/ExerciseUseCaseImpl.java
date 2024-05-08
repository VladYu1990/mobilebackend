package com.istudyenglish.mobilebackend.application.exercise;

import com.istudyenglish.mobilebackend.adapter.exercise.ExerciseDAO;
import com.istudyenglish.mobilebackend.domain.dictionary.Source;
import com.istudyenglish.mobilebackend.port.in.exercise.ExerciseUseCase;
import com.istudyenglish.mobilebackend.port.out.exercise.ExerciseDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.istudyenglish.mobilebackend.domain.Education.Exercise;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class ExerciseUseCaseImpl implements ExerciseUseCase {

    ExerciseDBPort exerciseDBport;

    ExerciseCreator exerciseCreator;

    @Autowired
    public ExerciseUseCaseImpl(ExerciseDAO exerciseDBport, ExerciseCreator exerciseCreator) {
        this.exerciseDBport = exerciseDBport;
        this.exerciseCreator = exerciseCreator;
    }

    public Exercise get(UUID uuid){

        return exerciseDBport.get(uuid);
    }

    public List<Exercise> get(List<String> strings){

        return exerciseDBport.get(strings);
    }

    public void create(Source source) {
        exerciseCreator.create(source);
    }

    public void delete(Exercise exercise) {
        exerciseDBport.delete(exercise);
    }

}
