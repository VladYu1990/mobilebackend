package com.istudyenglish.mobilebackend.application.token;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.userService.domain.Token;
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
        killTokensForUser(userUUID);
        Token token = new Token(userUUID);
        tokenDBPort.create(token);
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
            //todo очень сомнительное решение, нужно передумать
            token = new Token(userUUID);
            tokenDBPort.create(token);
        }
        return token;
    }

    //todo перенести в токен
    public boolean isAlive(Token token) {
        return token.checkTokenIsALife();
    }

    public void killTokensForUser(UUID userUUID) {
        try {
            Collection<Token> tokens = tokenDBPort.getTokensAliveByUserUUID(userUUID);
            for(Token t:tokens){
                t.kill();
            }
            tokenDBPort.killTokens(tokens);
        }
        catch (CustomException e) {
        }
    }
}
