package com.istudyenglish.mobilebackend.userService.interfaces.external;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.userService.domain.Token;

import java.util.UUID;

public interface UserUseCases {
    public void create(String login,String password);
    public void create(String login,String password,String phoneNumber);
    public UUID logIn(String login,String password) throws CustomException;
    public void logOut(String token);
    public void validateToken(String token, String user) throws CustomException;
}
