package com.istudyenglish.mobilebackend.application.user;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.CustomException.CustomExceptionCode;
import com.istudyenglish.mobilebackend.domain.Autorisation.PasswordValidator;
import com.istudyenglish.mobilebackend.port.out.user.UserDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.in.user.UserUseCase;

import java.util.UUID;

@Service
public class UserUseCaseImpl implements UserUseCase {

    UserDBPort userDAO;
    PasswordValidator passwordValidator;


    @Autowired
     public UserUseCaseImpl(UserDBPort userDAO, PasswordValidator passwordValidator) {
        this.userDAO = userDAO;
        this.passwordValidator = passwordValidator;
    }

    public void create(String login, String password, String phoneNumber) throws CustomException{
        User user = new User(login,password,phoneNumber);
        userDAO.create(user);

    }

    public User logIn(String login,String password) throws CustomException {
        try {
            return userDAO.getByLoginAndPassword(login,password);
        }
        catch (NullPointerException npe){
                throw new CustomException(CustomExceptionCode.LoginAndPasswordDoNotExist);
            }
        }


    public User getByUUID(UUID userUUID) throws CustomException {
        try {
            User user = userDAO.getByUUID(userUUID);
            return user;
        }
        catch (NullPointerException npe){
            throw new CustomException(CustomExceptionCode.LoginAndPasswordDoNotExist);
        }
    }

    public String setNewPassword(User user, String password) {

        passwordValidator.validatePassword(password);
        if (passwordValidator.isValid()){
            user.setNewPassword(password);
            return "ok";
        }
        else {return passwordValidator.getMessage();}
    }

}
