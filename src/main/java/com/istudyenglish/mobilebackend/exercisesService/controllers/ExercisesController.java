package com.istudyenglish.mobilebackend.exercisesService.controllers;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.exercisesService.adapters.ExerciseForView;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.*;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCases;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCasesImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/exercise/")
@Log4j2
public class ExercisesController {

    UserUseCases userUseCases;
    ExerciseForViewUseCases exerciseForViewUseCases;

    @Autowired
    public ExercisesController(UserUseCasesImp userUseCasesImp, ExerciseForViewUseCasesImp exerciseForViewUseCasesImp) {
        this.userUseCases = userUseCasesImp;
        this.exerciseForViewUseCases = exerciseForViewUseCasesImp;
    }

    @GetMapping("/next/{count}")
    public List<ExerciseForView> getNext(@RequestHeader Map<String, String> headers,
                                         @RequestParam int count) throws CustomException {

        userUseCases.validateToken(
                headers.get("token"),
                headers.get("user"));

        return exerciseForViewUseCases.getNextList(
                UUID.fromString(headers.get("user")),
                count);
    }

}
