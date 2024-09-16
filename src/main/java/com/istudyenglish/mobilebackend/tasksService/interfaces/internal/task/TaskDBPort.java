package com.istudyenglish.mobilebackend.tasksService.interfaces.internal.task;

import com.istudyenglish.mobilebackend.tasksService.domain.Task;

import java.util.List;
import java.util.UUID;

public interface TaskDBPort {

    public List<Task> getNext(UUID userUUID, int count);
    public Task genOnUUID(UUID task);
    public void create(Task task);
    public void update(Task task);

}
