package com.istudyenglish.mobilebackend.application.student;

import com.istudyenglish.mobilebackend.adapter.student.StudentDAO;
import com.istudyenglish.mobilebackend.application.user.UserUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Education.Languages;
import com.istudyenglish.mobilebackend.port.out.student.StudentDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.port.in.student.StudentUseCase;

import java.time.Instant;
import java.util.UUID;

@Component
public class StudentUseCaseImpl implements StudentUseCase{

    StudentDBPort studentDAO;

    UserUseCaseImpl userUseCase;

    @Autowired
    public StudentUseCaseImpl(StudentDAO studentDAO, UserUseCaseImpl userUseCase) {
        this.studentDAO = studentDAO;
        this.userUseCase = userUseCase;
    }

    public void create(UUID userUUID , String fullName, Languages nativeLanguages, Languages languagesForStudy, Instant instant) {
        Student student =
                Student.builder().
                        uuid(UUID.randomUUID()).
                        code(generateCode(fullName,nativeLanguages,languagesForStudy)).
                        userUuid(userUUID).
                        studentFullName(fullName).
                        nativeLanguages(nativeLanguages).
                        languagesForStudy(languagesForStudy).
                        dateCreated(instant).
                        build();
        studentDAO.save(student);
    }



    public Student get(UUID studentUUID) {

        return studentDAO.get(studentUUID);
    }

    public void rename(Student student,String newFullName) {
        student.setStudentFullName(newFullName);
        studentDAO.update(student);
    }

    @Override
    public boolean checkAffilationUser(Student student, UUID uuid) {
        return student.checkUser(uuid);
    }

    private String generateCode(String fullName, Languages nativeLanguages, Languages languagesForStudy) {
        String str = fullName + "/" + nativeLanguages.toString() + "/" + languagesForStudy.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        while (studentDAO.checkForExistence(stringBuilder.toString())){
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            Integer i =  (int) (Math.random()*10000);
            stringBuilder.append("/" + i.toString());
        }

        return stringBuilder.toString();
    }
}
