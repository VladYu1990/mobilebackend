package com.istudyenglish.mobilebackend.application.task;

import org.springframework.stereotype.Component;
import com.istudyenglish.mobilebackend.application.exercise.ExerciseUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Education.Exercise;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.domain.Education.Task.TaskStatus;

@Component
public class TaskReadinessForStudyCheck {

    private ExerciseUseCaseImpl exerciseUseCaseImpl;


    private TaskStatus readyToStudyCheckInterior(Student student, Exercise exercise){

        //todo
        if (true){
            return TaskStatus.READY;
        }
        else {
            return TaskStatus.NOT_READY;
        }
    }

    public TaskStatus readyToStudyCheck(Student student, Task task){
        return readyToStudyCheckInterior(student, exerciseUseCaseImpl.get(task.getExerciseUUID()));
    }
}
