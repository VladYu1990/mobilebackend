package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.application.exercise.ExerciseUseCaseImpl;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskAnswerChecker {

    private ExerciseUseCaseImpl exerciseUseCase;

    private Task task;

    public boolean check(Task task, UUID answerUUID) {
        this.task = task;
        return checkIn(answerUUID);
    }


    private boolean checkIn(UUID answerUUID) {

        if (getTrueAnswerUUID().equals(answerUUID)) {
            return true;
        } else {
            return false;
        }
    }

    private UUID getTrueAnswerUUID() {
        //todo
        //return exerciseUseCase.get(task.getExerciseUUID()).getAnswerUUID();
        return null;
    }
}

