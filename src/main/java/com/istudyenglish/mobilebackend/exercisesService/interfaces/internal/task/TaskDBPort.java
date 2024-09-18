package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.task;

import com.istudyenglish.mobilebackend.exercisesService.domain.Task;

import java.util.List;
import java.util.UUID;

public interface TaskDBPort {

    public List<Task> getNextOnlyStudy(UUID userUUID, int count);
    public Task genOnUUID(UUID task);
    public void create(Task task);
    public void update(Task task);

    Task getByUserAndExercise(UUID userUUID, UUID exerciseUUID);
}
