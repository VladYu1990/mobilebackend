package com.istudyenglish.mobilebackend.port.in.token;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.domain.Autorisation.Token;

import java.util.UUID;

public interface TokenUseCase {
    void create(UUID userUUID);
    Token get(UUID uuid) throws CustomException;
    Token getByUserUUID(UUID userUUID);
    boolean isAlive(Token token);
    void killTokensForUser(UUID userUUID);
}
