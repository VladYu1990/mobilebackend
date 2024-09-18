package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise.ExerciseDAO;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise.ExerciseDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
public class ExerciseUseCasesImp implements ExerciseUseCases {
    ExerciseDBPort exerciseDBPort;


    @Autowired
    public ExerciseUseCasesImp(ExerciseDAO exerciseDAO) {
        this.exerciseDBPort = exerciseDAO;

    }

    @Override
    public List<Exercise> getOnUUIDs(List<UUID> uuidList) {
        return List.of();
    }

    @Override
    public Exercise getOnUUID(UUID exerciseUUID){
        return null;
    }

    @Override
    public void create(UUID wordUUID) {

    }

    @Override
    public boolean checkAnswer(UUID exerciseUUID,UUID answerUUID) {
        Exercise exercise = getOnUUID(exerciseUUID);
        return exercise.checkAnswer(answerUUID);
    }
}
