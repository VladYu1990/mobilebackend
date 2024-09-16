package com.istudyenglish.mobilebackend.dictionary.controllers;

import com.istudyenglish.mobilebackend.adapter.Response;
import com.istudyenglish.mobilebackend.adapter.Validators.TokenValidator;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.dictionary.interfaces.external.WordUseCasesImp;
import com.istudyenglish.mobilebackend.dictionary.interfaces.external.WordsUseCases;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/word/")
@Log4j2
public class WordController {

    private WordsUseCases wordUseCases;
    private TokenValidator tokenValidator;


    @Autowired
    public WordController(WordUseCasesImp wordUseCasesImp, TokenValidator tokenValidator) {
        this.wordUseCases = wordUseCasesImp;
        this.tokenValidator = tokenValidator;
    }

    @GetMapping("/getUUID/{uuidString}")
    public Response getNext(@RequestHeader Map<String, String> headers,
                            @PathVariable String uuidString) {
        try {
            //TODO
            tokenValidator.check(headers.get("token"));

            UUID uuid = UUID.fromString(uuidString);


            return new Response("ok",wordUseCases.getUUID(uuid));
        }
        catch (CustomException customException){
            return new Response("error",customException);
        }
    }




}
