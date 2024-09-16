package com.istudyenglish.mobilebackend.userService.controllers;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCases;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCasesImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users/")
@Log4j2
public class UserController {

    private UserUseCases userUseCases;


    @Autowired
    public UserController(UserUseCasesImp userUseCasesImp) {
        this.userUseCases = userUseCasesImp;
    }

    @PostMapping("/create")
    public void create(@RequestHeader Map<String, String> headers){
        if(headers.get("phoneNumber") == null){
            userUseCases.create(headers.get("login"),headers.get("password"));
        }
        else{
            userUseCases.create(headers.get("login"),headers.get("password"),headers.get("phoneNumber"));
        }
    }

    @PutMapping("/logIn")
    public UUID logIn(@RequestHeader Map<String, String> headers) throws CustomException {
        //TODO глянуть как отрабатывает выкидывание ошибок и если не ок поправить
        return userUseCases.logIn(headers.get("login"),headers.get("password"));
    }

    @PutMapping("/logOut")
    public void logOut(@RequestHeader Map<String, String> headers) {
        userUseCases.logOut(headers.get("token"));
    }

    @GetMapping("/validateToken")
    public void validateToken(@RequestHeader Map<String, String> headers) throws CustomException {
        userUseCases.validateToken(headers.get("token"),headers.get("user"));
    }

}
