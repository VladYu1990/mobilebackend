package com.istudyenglish.mobilebackend.port.in.user;


import com.istudyenglish.mobilebackend.domain.Autorisation.User;

import java.time.Instant;
import java.util.UUID;

public interface UserUseCase {
    void create(String login, String password, String phoneNumber);
    User logIn(String login, String password);
    User get(String tokenStr) throws Exception;
    void block(User user);
    void unblock(User user);
    boolean checkToken(User user);
    void setNewPassword(User user,String password);
}
