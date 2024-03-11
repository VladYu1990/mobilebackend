package com.istudyenglish.mobilebackend.adapter.Validators;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.CustomException.CustomExceptionCode;
import com.istudyenglish.mobilebackend.application.token.TokenUseCaseImp;
import com.istudyenglish.mobilebackend.application.user.UserDAO;
import com.istudyenglish.mobilebackend.application.user.UserUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Autorisation.Token;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.in.token.TokenUseCase;
import com.istudyenglish.mobilebackend.port.in.user.UserUseCase;
import com.istudyenglish.mobilebackend.port.out.User.UserDBPort;
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
        UUID tokenUUID = UUID.fromString(token);
        check(tokenUUID);
        }

    public void check(UUID tokenUUID) throws CustomException {
        Token token;
        token = tokenUseCase.get(tokenUUID);
        check(token);
    }
    public void check(Token token) throws CustomException {
        if(!token.checkTokenIsALife()){
            throw new CustomException(CustomExceptionCode.TokenIsDead);
        }
    }
}

