package com.istudyenglish.mobilebackend.dictionary.controllers;


import com.istudyenglish.mobilebackend.dictionary.interfaces.external.SourceUseCasesImp;
import com.istudyenglish.mobilebackend.dictionary.interfaces.external.SourceUseCases;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/word/")
@Log4j2
public class TextController {

    private SourceUseCases sourceUseCases;

    @Autowired
    public TextController(SourceUseCasesImp wordsUseCases) {
        this.sourceUseCases = wordsUseCases;
    }

    @PostMapping("create/")
    public void create(@RequestHeader Map<String,String> headers,
                       @RequestBody Map<String,String> body){


        sourceUseCases.create(body);

    }

}
