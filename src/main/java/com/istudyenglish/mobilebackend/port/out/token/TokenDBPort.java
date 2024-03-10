package com.istudyenglish.mobilebackend.port.out.token;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.domain.Autorisation.Token;

import java.util.Collection;
import java.util.UUID;

public interface TokenDBPort {
    void create(Token token);
    Token get(UUID uuid) throws CustomException;
    Token getTokenAliveByUserUUID(UUID userUUID) throws CustomException;
    Collection<Token> getTokensByUserUUID(UUID userUUID) throws CustomException;
    void killTokens(Collection<Token> tokens) throws CustomException;
}
