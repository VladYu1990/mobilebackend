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

    @PostMapping("/create/{login}/{password}")
    public void create(@RequestHeader Map<String, String> headers,
                       @PathVariable String login,@PathVariable String password,
                       @RequestParam(defaultValue = "") String time) {

        Instant instant = Instant.now();
        if(!time.equals("")){
            Instant.parse(time);
        }
        userUseCase.create(login,password, instant);
    }

    @GetMapping("/login/{login}/{password}")
    public Response getNext(@RequestHeader Map<String, String> headers,
                            @PathVariable String login, @PathVariable String password,
                            @RequestParam(defaultValue = "") String time){
        Instant instant = Instant.now();
        if(!time.equals("")){
            Instant.parse(time);
        }
        User user = userUseCase.logIn(login,password);
        return new Response("ок",user.getToken());
    }

}
