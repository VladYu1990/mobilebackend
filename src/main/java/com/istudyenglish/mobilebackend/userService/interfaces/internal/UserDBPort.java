package com.istudyenglish.mobilebackend.userService.interfaces.internal;

import com.istudyenglish.mobilebackend.dictionary.domain.Word;
import com.istudyenglish.mobilebackend.userService.domain.Token;
import com.istudyenglish.mobilebackend.userService.domain.User;

import java.util.UUID;

public interface UserDBPort {

    public void createUser(User user);
    public User getUUID(UUID userUUID);
    public User getUserViaLoginPassword(String login,String password);
    public User getUserViaToken(UUID uuid);

    void createToken(User user);

    public void deleteToken(User user);

}
