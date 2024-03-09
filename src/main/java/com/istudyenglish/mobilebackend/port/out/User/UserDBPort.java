package com.istudyenglish.mobilebackend.port.out.User;

import com.istudyenglish.mobilebackend.domain.Autorisation.User;

import java.util.UUID;

public interface UserDBPort {

    void create(User user);

    User get(UUID uuid) throws Exception;

    User get(String tokenStr) throws Exception;

    User getLogin(String login) throws Exception;

    User get(String login, String token);

    void update(User user);
}
