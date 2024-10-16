package com.istudyenglish.mobilebackend.exercisesService.controllers;


import com.istudyenglish.mobilebackend.CustomException;
import com.istudyenglish.mobilebackend.exercisesService.adapters.ExerciseForView;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.ExerciseForViewUseCases;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.ExerciseForViewUseCasesImp;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCases;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCasesImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/test/")
@Log4j2
public class TestController {

    @GetMapping()
    public String getNext(){
        return "запрос выполнен успешно, систем доступна";
    }
}
