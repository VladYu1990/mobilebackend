package com.istudyenglish.mobilebackend.adapter.Validators;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.CustomException.CustomExceptionCode;
import com.istudyenglish.mobilebackend.application.token.TokenUseCaseImp;
import com.istudyenglish.mobilebackend.userService.domain.Token;
import com.istudyenglish.mobilebackend.port.in.token.TokenUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenValidator {

    TokenUseCase tokenUseCase;

    @Autowired
    public TokenValidator(TokenUseCaseImp tokenUseCase) {
        this.tokenUseCase = tokenUseCase;
    }

    public void check(String token) throws CustomException {
        try {
            UUID tokenUUID = UUID.fromString(token);
            Token token1 = tokenUseCase.get(UUID.fromString(token));
        } catch (CustomException ce){
            new CustomException(CustomExceptionCode.TokenDoNotExist,"токен не существует");

        } catch (Exception e) {
            new CustomException(CustomExceptionCode.TokenDoNotValid, "не верный формат токена");
        }


    }
}

