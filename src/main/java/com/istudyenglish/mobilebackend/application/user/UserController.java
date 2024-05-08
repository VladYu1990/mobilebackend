package com.istudyenglish.mobilebackend.application.user;

import com.istudyenglish.mobilebackend.adapter.Response;
import com.istudyenglish.mobilebackend.adapter.Validators.TokenValidator;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.token.TokenConvertor;
import com.istudyenglish.mobilebackend.application.token.TokenUseCaseImp;
import com.istudyenglish.mobilebackend.domain.Autorisation.Token;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.in.token.TokenUseCase;
import com.istudyenglish.mobilebackend.port.in.user.UserUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users/")
@Log4j2
public class UserController {
    TokenValidator tokenValidator;
    TokenConvertor tokenConvertor;
    TokenUseCase tokenUseCase;
    UserUseCase userUseCase;

    @Autowired
    public UserController(TokenValidator tokenValidator, TokenConvertor tokenConvertor, TokenUseCaseImp tokenUseCase, UserUseCaseImpl userUseCase) {
        this.tokenValidator = tokenValidator;
        this.tokenConvertor = tokenConvertor;
        this.tokenUseCase = tokenUseCase;
        this.userUseCase = userUseCase;
    }

    @PostMapping("/create/")
    public Response create(@RequestHeader Map<String, String> headers) {

        Response response = new Response();

        try {
            log.info(headers.toString());
            userUseCase.create(
                    headers.get("login"),
                    headers.get("password"),
                    headers.get("phone"));
            response.setRequest_status("ok");
            response.setResult_object("");
        }
        catch (CustomException customException){
            response.setRequest_status("error");
            response.setResult_object(customException.getMessage());
        }
        return response;

    }

    @GetMapping( "/logIn/")
    public Response logIn(@RequestHeader Map<String, String> headers){
        Response response = new Response();
        log.info(headers.toString());

        try {

            User user = userUseCase.logIn(
                    headers.get("login"),
                    headers.get("password"));
            Token token = tokenUseCase.getByUserUUID(user.getUuid());
            response.setRequest_status("ok");
            response.setResult_object(token);

        }
        catch (CustomException customException){
            response.setRequest_status("error");
            response.setResult_object(customException.getMessage());
        }
        log.warn(response.toString());
        return response;


    }

    /**@PutMapping("/new_password/")
    public Response setNewPassword(@RequestHeader Map<String, String> headers) {
        try {
            tokenValidator.check(headers.get("token"));
            User user = tokenConvertor.getUser(headers.get("token"));
            userUseCase.setNewPassword(user,headers.get("new_password"));

            return new Response("ok","");

        }
        catch (CustomException customException){
            return new Response("error",customException.getMessage());
        }
    }**/
}
