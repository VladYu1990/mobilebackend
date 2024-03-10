package com.istudyenglish.mobilebackend.application.token;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.domain.Autorisation.Token;
import com.istudyenglish.mobilebackend.port.in.token.TokenUseCase;
import com.istudyenglish.mobilebackend.port.out.token.TokenDBPort;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

@Component
public class TokenUseCaseImp implements TokenUseCase {

    TokenDBPort tokenDBPort;


    public TokenUseCaseImp(TokenDAO tokenDAO) {
        this.tokenDBPort = tokenDAO;
    }

    public void create(UUID userUUID) {
        getByUserUUID(userUUID);
    }

    public Token get(UUID uuid) throws CustomException {
        return  tokenDBPort.get(uuid);
    }

    public Token getByUserUUID(UUID userUUID) {
        Token token;
        try {
            token = tokenDBPort.getTokenAliveByUserUUID(userUUID);
        }
        catch (CustomException customException){
            token = new Token(userUUID);
            tokenDBPort.create(token);
        }
        return token;
    }

    public boolean isAlive(Token token) {
        return token.checkTokenIsALife();
    }

    public void killOldTokenForUser(UUID userUUID) {
        try {
            Collection<Token> tokens = tokenDBPort.getTokensByUserUUID(userUUID);
            tokenDBPort.killTokens(tokens);
        }
        catch (CustomException e) {
        }
    }
}
