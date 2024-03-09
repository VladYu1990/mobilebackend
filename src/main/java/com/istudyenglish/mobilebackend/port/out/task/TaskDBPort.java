package com.istudyenglish.mobilebackend.port.out.task;

import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

public interface TaskDBPort {

    Task getByUUID(UUID uuid);

    Collection<Task> getNext(Student student,Instant instant);

    void save(Task task, Instant instant);
    void save(Collection<Task> taskCollection, Instant instant);

    void update(Task task, Instant instant);
    void update(Collection<Task> taskCollection, Instant instant);

    void delete(Collection<Task> taskCollection, Instant instant);

    Task getByCode(String code);
}
