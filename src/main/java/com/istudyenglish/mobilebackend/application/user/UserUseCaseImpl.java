package com.istudyenglish.mobilebackend.application.user;

import com.istudyenglish.mobilebackend.port.out.User.UserDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.in.user.UserUseCase;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserUseCaseImpl implements UserUseCase {

    UserDBPort userDAO;


    @Autowired
    public UserUseCaseImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void create(String login, String password, String phoneNumber) {
        User user = new User(login,password,phoneNumber);
        userDAO.create(user);

    }

    public User logIn(String login, String password) {
        return userDAO.get(login,password);



    }

    public User get(String tokenStr) throws Exception {
        return userDAO.get(tokenStr);
    }

    public void block(User user) {
        user.setActive(false);
        userDAO.update(user);
    }

    public void unblock(User user) {
        user.setActive(true);
        userDAO.update(user);
    }

    public boolean checkToken(User user) {
       return user.checkToken();
    }

    public void setNewPassword(User user, String password) {
        user.setNewPassword(password);
    }
}
