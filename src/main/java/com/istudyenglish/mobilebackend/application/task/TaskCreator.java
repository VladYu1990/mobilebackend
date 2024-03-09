package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.application.exercise.ExerciseUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Education.*;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.domain.Education.Task.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class TaskCreator {

    private Exercise exercise;
    private UUID studentUUID;
    private Instant instantCreate;
    private ExerciseUseCaseImpl exerciseUseCaseImpl;

    @Autowired
    public TaskCreator(ExerciseUseCaseImpl exerciseUseCase) {
        this.exerciseUseCaseImpl = exerciseUseCase;
    }

    public Task create(UUID studentUUID, UUID exerciseUUID) {
        this.studentUUID = studentUUID;
        setExercise(exerciseUUID);
        this.instantCreate = Instant.now();


        return createTask();
    }

    public Task create(UUID studentUUID, UUID exerciseUUID, Instant instant) {
        this.studentUUID = studentUUID;
        this.instantCreate = instant;

        return createTask();
    }

    public Task create(Student student, Exercise exercise,Instant instant) {
        this.studentUUID = student.getUuid();
        this.instantCreate = instant;
        return createTask();
    }

    private void setExercise(UUID exerciseUUID) {
        this.exercise = exerciseUseCaseImpl.get(exerciseUUID);
    }


    private Task createTask(){

        return Task.builder().
                uuid(UUID.randomUUID()).
                exerciseUUID(exercise.getUuid()).
                studentUUID(studentUUID).
                nextRepetition(Instant.now()).
                lastRepetition(Instant.now()).
                status(TaskStatus.NOT_READY).
                countRightResponses(0)
                .build();
    }
}
