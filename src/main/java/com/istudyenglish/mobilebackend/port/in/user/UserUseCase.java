package com.istudyenglish.mobilebackend.port.in.user;


import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.userService.domain.User;

import java.util.UUID;

public interface UserUseCase {
    void create(String login, String password, String phoneNumber) throws CustomException;
    User logIn(String login, String password) throws CustomException;
    User getByUUID(UUID userUUID) throws CustomException;
    String setNewPassword(User user,String password);
}
