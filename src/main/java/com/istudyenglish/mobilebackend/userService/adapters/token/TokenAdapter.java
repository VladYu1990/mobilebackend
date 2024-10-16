package com.istudyenglish.mobilebackend.userService.adapters.token;

import com.istudyenglish.mobilebackend.userService.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenAdapter {

    @Autowired
    public TokenAdapter() {
    }

    public Token adapt(User user){
        return Token.builder().
                uuid(user.getToken()).
                dateOfDeathToken(user.getDateOfDeathToken()).
                build();
    }
}
