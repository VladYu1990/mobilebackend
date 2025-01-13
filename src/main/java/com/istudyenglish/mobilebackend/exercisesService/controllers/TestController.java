package com.istudyenglish.mobilebackend.exercisesService.controllers;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/")
@Log4j2
public class TestController {

    @GetMapping()
    public String getNext(){
        return "запрос выполнен успешно, систем доступна";
    }
}
