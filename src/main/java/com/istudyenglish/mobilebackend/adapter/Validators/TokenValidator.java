package com.istudyenglish.mobilebackend.adapter.Validators;

import com.istudyenglish.mobilebackend.application.user.UserDAO;
import com.istudyenglish.mobilebackend.application.user.UserUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.in.user.UserUseCase;
import com.istudyenglish.mobilebackend.port.out.User.UserDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenValidator {

    UserDBPort userDBPort;
    UserUseCase userUseCase;


    @Autowired
    public TokenValidator(UserDAO userDBPort, UserUseCaseImpl userUseCase) {
        this.userDBPort = userDBPort;
        this.userUseCase = userUseCase;
    }

    public void check(String token) throws Exception{
        try {
            User user = userDBPort.getByUUID(token);
            if (userUseCase.checkToken(user)){
                throw new Exception("Token is old");
            }
        }
        catch (NullPointerException npe){
            throw new Exception("Token doesn't exist");
        }
    }
}
