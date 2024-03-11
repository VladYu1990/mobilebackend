package com.istudyenglish.mobilebackend.port.in.student;

import com.istudyenglish.mobilebackend.domain.Education.Languages;
import com.istudyenglish.mobilebackend.domain.Education.Student;

import java.time.Instant;
import java.util.UUID;

public interface StudentUseCase {
    void create(UUID userUUID, String fullName, Languages nativeLanguages, Languages languagesForStudy, Instant instant);
    Student get(UUID studentUUID);
    void rename(Student student,String newFullName);

    boolean checkAffilationUser(Student student, UUID uuid);
}
