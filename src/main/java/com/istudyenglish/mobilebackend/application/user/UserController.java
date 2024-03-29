package com.istudyenglish.mobilebackend.application.user;

import com.istudyenglish.mobilebackend.adapter.Response;
import com.istudyenglish.mobilebackend.adapter.Validators.TokenValidator;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.token.TokenConvertor;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
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
    UserUseCase userUseCase;

    @Autowired
    public UserController(TokenValidator tokenValidator, TokenConvertor tokenConvertor, UserUseCaseImpl userUseCase) {
        this.tokenValidator = tokenValidator;
        this.tokenConvertor = tokenConvertor;
        this.userUseCase = userUseCase;
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

    @PutMapping("/new_password/")
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
    }
}
