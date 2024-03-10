package com.istudyenglish.mobilebackend.application.user;

import com.istudyenglish.mobilebackend.adapter.Response;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.token.TokenUseCaseImp;
import com.istudyenglish.mobilebackend.domain.Autorisation.Token;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.in.token.TokenUseCase;
import com.istudyenglish.mobilebackend.port.in.user.UserUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users/")
@Log4j2
public class UserController {
    UserUseCase userUseCase;
    TokenUseCase tokenUseCase;

    @Autowired
    public UserController(UserUseCaseImpl userUseCase, TokenUseCaseImp tokenUseCase) {
        this.userUseCase = userUseCase;
        this.tokenUseCase = tokenUseCase;
    }

    @PostMapping("/create/")
    public Response create(@RequestHeader Map<String, String> headers) {

        Response response = new Response();

        try {
            userUseCase.create(
                    headers.get("login"),
                    headers.get("password"),
                    headers.get("phoneNumber"));
            response.setRequest_status("ok");
            response.setResult_object("");

        }
        catch (CustomException customException){
            response.setRequest_status("error");
            response.setResult_object(customException.getMessage());
        }
        return response;
    }

    @GetMapping("/login/")
    public Response login(@RequestHeader Map<String, String> headers){
         try {
            User user;
            user = userUseCase.logIn(
                    headers.get("login"),
                    headers.get("password"));

            Token token;
            token = tokenUseCase.getByUserUUID(user.getUuid());

            return new Response("ok",token);

        }
        catch (CustomException customException){
            return new Response("error",customException.getMessage());
        }
    }

    @PutMapping("/new_password/")
    public Response setNewPassword(@RequestHeader Map<String, String> headers) {
        try {

            UUID uuid = UUID.fromString(headers.get("token"));

            Token token = tokenUseCase.get(uuid);
            User user = userUseCase.getByUUID(token.getUserUUID());
            userUseCase.setNewPassword(user,headers.get("new_password"));

            return new Response("ok","");

        }
        catch (CustomException customException){
            return new Response("error",customException.getMessage());
        }
    }
}
