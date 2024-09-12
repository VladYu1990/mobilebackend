package com.istudyenglish.mobilebackend.adapter.Validators;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.CustomException.CustomExceptionCode;
import com.istudyenglish.mobilebackend.application.token.TokenUseCaseImp;
import com.istudyenglish.mobilebackend.userService.domain.Token;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.port.in.student.StudentUseCase;
import com.istudyenglish.mobilebackend.port.in.token.TokenUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenExistStudentValidator {

    TokenUseCase tokenUseCase;
    StudentUseCase studentUseCase;

    @Autowired
    public TokenExistStudentValidator(TokenUseCaseImp tokenUseCase) {
        this.tokenUseCase = tokenUseCase;
    }

    public void check(String tokenStr,String studentStr) throws CustomException {

        Token token = tokenUseCase.get(UUID.fromString(tokenStr));
        Student student = studentUseCase.get(UUID.fromString(studentStr));
        if(!token.getUserUUID().equals(student.getUserUuid())) {
            throw new CustomException(CustomExceptionCode.TokenDoNotExistForStudent, "токен не сушествует для студента");
        }
    }
}

