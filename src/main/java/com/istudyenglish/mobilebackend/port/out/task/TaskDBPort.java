package com.istudyenglish.mobilebackend.port.out.task;

import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

public interface TaskDBPort {

    Task get(UUID uuid);

    Collection<Task> getNext(Student student,Instant instant);

    void save(Task task, Instant instant);
    void save(Collection<Task> taskCollection, Instant instant);

    void update(Task task);
    void update(Collection<Task> taskCollection);
}
