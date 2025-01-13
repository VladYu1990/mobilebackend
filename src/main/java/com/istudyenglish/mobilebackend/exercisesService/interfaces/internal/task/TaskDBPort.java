package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.task;

import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.userService.domain.User;

import java.util.List;
import java.util.UUID;

public interface TaskDBPort {

    public List<Task> getNextOnlyStudy(User user, int count);
    public Task genOnUUID(UUID task);
    public void create(List<Task> taskList);
    public void update(Task task);

    Task getByUserAndExercise(UUID userUUID, UUID exerciseUUID);
}
