package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.application.exercise.ExerciseUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Education.*;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.tasksService.domain.TaskStatus;
import com.istudyenglish.mobilebackend.tasksService.domain.exercise.Exercise;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class TaskCreator {

    public Task create(UUID studentUUID, UUID exerciseUUID) {
        return Task.builder().
                uuid(UUID.randomUUID()).
                exerciseUUID(exerciseUUID).
                studentUUID(studentUUID).
                nextRepetition(Instant.now()).
                lastRepetition(Instant.now()).
                status(TaskStatus.READY).
                countRightResponses(0).
                build();
    }

    public Task create(Student student, Exercise exercise) {
        return create(student.getUuid(),exercise.getUuid());
    }

}
