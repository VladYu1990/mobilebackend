package com.istudyenglish.mobilebackend.userService.interfaces.external;


import com.istudyenglish.mobilebackend.CustomException;
import com.istudyenglish.mobilebackend.userService.domain.User;

import java.util.UUID;

public interface UserUseCases {
    public void create(String login,String password,String phoneNumber);
    public User logIn(String login, String password) throws CustomException;
    public void logOut(String token);
    public void validateToken(String token, String user) throws CustomException;
}
