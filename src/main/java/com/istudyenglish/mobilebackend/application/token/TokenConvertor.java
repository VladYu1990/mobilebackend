package com.istudyenglish.mobilebackend.application.token;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.user.UserUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Autorisation.Token;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.in.token.TokenUseCase;
import com.istudyenglish.mobilebackend.port.in.user.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenConvertor {
    UserUseCase userUseCase;
    TokenUseCase tokenUseCase;


    @Autowired
    public TokenConvertor(UserUseCaseImpl userUseCase) {
        this.userUseCase = userUseCase;
    }

    public User getUser(String tokenStr) throws CustomException {

        UUID tokenUUID = UUID.fromString(tokenStr);
        Token token = tokenUseCase.get(tokenUUID);
        return getUser(token);
    }

    public User getUser(Token token) throws CustomException {
        UUID userUUID = token.getUserUUID();
        return userUseCase.getByUUID(userUUID);
    }

}
