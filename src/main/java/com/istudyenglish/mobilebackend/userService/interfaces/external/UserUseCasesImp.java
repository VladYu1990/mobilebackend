package com.istudyenglish.mobilebackend.userService.interfaces.external;


import com.istudyenglish.mobilebackend.CustomException;
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
    public void create(String login, String password,String phoneNumber) {
        //TODO добавить ошибку, если такой юзер существует
        User user = new User(login,password,phoneNumber);
        userDBPort.createUser(user);
    }

    @Override
    public User logIn(String login, String password) throws CustomException {
        User user = userDBPort.getUserViaLoginPassword(login,password);
        if(user == null){
            throw new CustomException();
        }
        if(!user.isTokenAlive()){
            user.createToken();
        }

        return user;
    }

    @Override
    public void logOut(String token) {
        User user = userDBPort.getUserViaToken(UUID.fromString(token));
        userDBPort.deleteToken(user);

    }

    @Override
    public void validateToken(String token, String userStr) throws CustomException {
        UUID tokenUUID = UUID.fromString(token);
        UUID userUUID = UUID.fromString(userStr);

        User user = userDBPort.getUUID(userUUID);
        if(!user.isTokenAlive() || user.getToken().equals(tokenUUID)){
            throw new CustomException();
        }

    }
}
