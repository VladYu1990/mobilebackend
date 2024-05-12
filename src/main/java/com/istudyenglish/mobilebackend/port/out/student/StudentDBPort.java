package com.istudyenglish.mobilebackend.port.out.student;

import com.istudyenglish.mobilebackend.domain.Education.Student;

import java.util.Collection;
import java.util.UUID;

public interface StudentDBPort {

    void save(Student student);

    Student get(UUID studentUUID);
    Student get(String studentCode);

    Collection<Student> get(Collection<UUID> uuidCollection);

    void update(Student student);

    boolean checkForExistence(String str);
}
