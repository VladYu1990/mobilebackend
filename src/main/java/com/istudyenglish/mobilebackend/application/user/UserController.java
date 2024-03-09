package com.istudyenglish.mobilebackend.application.user;

import com.istudyenglish.mobilebackend.adapter.Response;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.in.user.UserUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users/")
@Log4j2
public class UserController {
    UserUseCase userUseCase;

    @Autowired
    public UserController(UserUseCaseImpl userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping("/create/")
    public void create(@RequestHeader Map<String, String> headers) {

        userUseCase.create(
                headers.get("login"),
                headers.get("password"),
                headers.get("phoneNumber"));
    }

    @GetMapping("/login/")
    public Response login(@RequestHeader Map<String, String> headers){

        return new Response("ок",
                userUseCase.logIn(
                    headers.get("login"),
                    headers.get("password")));
    }

    @PutMapping("/new_password/")
    public Response setNewPassword(@RequestHeader Map<String, String> headers) {
        try {
            User user = userUseCase.get(
                    headers.get("token"));
            user.setNewPassword("password");
            return new Response("ok");
        }
        catch (Exception e){
            return new Response("ok",e);
        }



    }
}
