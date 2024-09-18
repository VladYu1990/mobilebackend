package com.istudyenglish.mobilebackend.application.answer;

import com.istudyenglish.mobilebackend.application.exercise.ExerciseUseCaseImpl;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.port.in.exercise.ExerciseUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerChecker {
    ExerciseUseCase exerciseUseCase;

    @Autowired
    public AnswerChecker(ExerciseUseCaseImpl exerciseUseCase) {
        this.exerciseUseCase = exerciseUseCase;
    }

    public boolean check(Task task, Answer answer){
        Exercise exercise;
        exercise = exerciseUseCase.get(task.getExerciseUUID());

        if(exercise.checkReply(answer.getValue())){
            return  true;
        }
        else {
            return false;
        }
    }
}
