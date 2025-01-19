package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.dictionary.domain.Source;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise.ExerciseDAO;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise.ExerciseDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ExerciseUseCasesImp implements ExerciseUseCases {
    private ExerciseDBPort exerciseDBPort;
    private BuilderExercises builderExercises;


    @Autowired
    public ExerciseUseCasesImp(ExerciseDAO exerciseDAO,BuilderExercises builderExercises) {
        this.exerciseDBPort = exerciseDAO;
        this.builderExercises = builderExercises;
    }

    @Override
    public List<Exercise> getOnUUIDs(List<UUID> uuidList) {

        return exerciseDBPort.genOnUUIDs(uuidList);
    }

    @Override
    public Exercise getOnUUID(UUID exerciseUUID){
        List<UUID> uuidList = new ArrayList<>();
        uuidList.add(exerciseUUID);

        return getOnUUIDs(uuidList).get(0);
    }

    @Override
    public List<Exercise> getAll() {
        return exerciseDBPort.getAll();
    }

    @Override
    public void create(Source source) {
        List<Exercise> exerciseList = builderExercises.build(source);
        for(Exercise exr:exerciseList){
            save(exr);
        }

    }

    @Override
    public boolean checkAnswer(Exercise exercise, Answer answer) {
        return exercise.checkAnswer(answer);
    }

    @Override
    public void save(Exercise exercise) {
        exerciseDBPort.save(exercise);
    }
}
