package com.istudyenglish.mobilebackend.port.out.User;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;

import java.util.UUID;

public interface UserDBPort {

    void create(User user) throws CustomException;

    User getByUUID(UUID uuid) throws NullPointerException;

    User getByLogin(String login) throws NullPointerException;

    void update(User user);
}
