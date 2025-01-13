package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise.ExerciseDAO;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise.ExerciseDBPort;
import com.istudyenglish.mobilebackend.userService.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;
import java.util.ArrayList;
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

        return exerciseDBPort.genOnUUIDs(uuidList);
    }

    @Override
    public Exercise getOnUUID(UUID exerciseUUID){
        List<UUID> uuidList = new ArrayList<>();
        uuidList.add(exerciseUUID);

        return getOnUUIDs(uuidList).get(0);
    }

    @Override
    public void create(Source source) {
        //todo it
    }

    @Override
    public boolean checkAnswer(Exercise exercise, Answer answer) {
        return exercise.checkAnswer(answer);
    }

}
