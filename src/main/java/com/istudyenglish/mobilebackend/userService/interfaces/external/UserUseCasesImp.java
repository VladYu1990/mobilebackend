package com.istudyenglish.mobilebackend.userService.interfaces.external;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.CustomException.CustomExceptionCode;
import com.istudyenglish.mobilebackend.dictionary.domain.Word;
import com.istudyenglish.mobilebackend.dictionary.interfaces.internal.WordDAO;
import com.istudyenglish.mobilebackend.userService.domain.Token;
import com.istudyenglish.mobilebackend.userService.domain.User;
import com.istudyenglish.mobilebackend.userService.interfaces.internal.UserDAO;
import com.istudyenglish.mobilebackend.userService.interfaces.internal.UserDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserUseCasesImp implements UserUseCases {

    private UserDBPort userDBPort;

    @Autowired
    public UserUseCasesImp(UserDAO userDAO) {
        this.userDBPort = userDAO;
    }


    @Override
    public void create(String login, String password) {
        //TODO добавить ошибку, если такой юзер существует
        User user = new User(login,password);
        userDBPort.createUser(user);
    }

    @Override
    public void create(String login, String password, String phoneNumber) {
        User user = new User(login,password,phoneNumber);
        userDBPort.createUser(user);
    }

    @Override
    public UUID logIn(String login, String password) throws CustomException {
        User user = userDBPort.getUserViaLoginPassword(login,password);
        if(user == null){
            throw new CustomException(CustomExceptionCode.LoginAndPasswordDoNotExist);
        }
        if(!user.isTokenAlive()){
            user.createToken();
        }

        return user.getToken();
    }

    @Override
    public void logOut(String token) {
        User user = userDBPort.getUserViaToken(UUID.fromString(token));
        userDBPort.deleteToken(user);

    }

    @Override
    public boolean validateToken(String token, String userStr) {
        User user = userDBPort.getUserViaToken(UUID.fromString(token));
        if(!user.isTokenAlive()){
            return false;
        }
        if(!user.getUuid().equals(UUID.fromString(userStr))){
            return false;
        }
        return true;
    }
}
