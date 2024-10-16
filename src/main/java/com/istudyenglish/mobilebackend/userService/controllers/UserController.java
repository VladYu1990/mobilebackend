package com.istudyenglish.mobilebackend.userService.controllers;


import com.istudyenglish.mobilebackend.CustomException;
import com.istudyenglish.mobilebackend.userService.adapters.token.Token;
import com.istudyenglish.mobilebackend.userService.adapters.token.TokenAdapter;
import com.istudyenglish.mobilebackend.userService.domain.User;
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
    private TokenAdapter tokenAdapter;


    @Autowired
    public UserController(UserUseCasesImp userUseCasesImp,TokenAdapter tokenAdapter) {
        this.userUseCases = userUseCasesImp;
        this.tokenAdapter = tokenAdapter;
    }

    @PostMapping("/create")
    public void create(@RequestHeader Map<String, String> headers){
        userUseCases.create(headers.get("login"),headers.get("password"),headers.get("phoneNumber"));
    }

    @PutMapping("/logIn")
    public Token logIn(@RequestHeader Map<String, String> headers) throws CustomException {
        //TODO глянуть как отрабатывает выкидывание ошибок и если не ок поправить
        User user = userUseCases.logIn(headers.get("login"),headers.get("password"));
        return tokenAdapter.adapt(user);
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
