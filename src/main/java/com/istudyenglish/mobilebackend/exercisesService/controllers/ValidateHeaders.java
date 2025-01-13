package com.istudyenglish.mobilebackend.exercisesService.controllers;

import com.istudyenglish.mobilebackend.CustomException;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCases;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCasesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class ValidateHeaders {
    private UserUseCases userUseCases;


    @Autowired
    public ValidateHeaders(UserUseCasesImp userUseCases) {
        this.userUseCases = userUseCases;
    }

    public void validateTokenAndUser(Map<String,String> header) throws CustomException {
        UUID userUUID = UUID.fromString(header.get("user"));
        UUID tokenUUID = UUID.fromString(header.get("token"));

        userUseCases.validateToken(tokenUUID, userUUID);
    }
}
