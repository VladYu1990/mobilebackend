package com.istudyenglish.mobilebackend.application;

import com.istudyenglish.mobilebackend.adapter.Response;
import com.istudyenglish.mobilebackend.adapter.Validators.TokenValidator;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.token.TokenConvertor;
import com.istudyenglish.mobilebackend.application.token.TokenUseCaseImp;
import com.istudyenglish.mobilebackend.application.user.UserUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Autorisation.Token;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.in.token.TokenUseCase;
import com.istudyenglish.mobilebackend.port.in.user.UserUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/authorization/")
@Log4j2
public class AuthorizationController {
    TokenValidator tokenValidator;
    TokenConvertor tokenConvertor;
    UserUseCase userUseCase;
    TokenUseCase tokenUseCase;

    @Autowired
    public AuthorizationController(TokenValidator tokenValidator, TokenConvertor tokenConvertor, UserUseCaseImpl userUseCase, TokenUseCaseImp tokenUseCase) {
        this.tokenValidator = tokenValidator;
        this.tokenConvertor = tokenConvertor;
        this.userUseCase = userUseCase;
        this.tokenUseCase = tokenUseCase;
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

    @PutMapping("/logout/")
    public Response setNewPassword(@RequestHeader Map<String, String> headers) {
        try {
            tokenValidator.check(headers.get("token"));
            User user;
            user = tokenConvertor.getUser(headers.get("token"));
            tokenUseCase.killTokensForUser(user.getUuid());
            return new Response("ok","");

        }
        catch (CustomException customException){
            return new Response("error",customException.getMessage());
        }
    }
}
