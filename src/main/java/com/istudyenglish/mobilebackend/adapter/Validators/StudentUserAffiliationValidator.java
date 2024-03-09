package com.istudyenglish.mobilebackend.adapter.Validators;

import com.istudyenglish.mobilebackend.application.student.StudentUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.port.in.student.StudentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentUserAffiliationValidator {

    StudentUseCase studentUseCase;

    @Autowired
    public StudentUserAffiliationValidator(StudentUseCaseImpl studentUseCase) {
        this.studentUseCase = studentUseCase;
    }

    public void check(User user, Student student) throws Exception {
        if(!studentUseCase.checkAffilationUser(student,user.getUuid())){
            throw new Exception("User and student aren't connected");
        }
    }
}
