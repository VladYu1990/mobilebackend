package com.istudyenglish.mobilebackend.exercisesService.controllers;


import com.istudyenglish.mobilebackend.CustomException;
import com.istudyenglish.mobilebackend.exercisesService.adapters.ExerciseForView;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.*;
import lombok.extern.log4j.Log4j2;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/exercises/")
@Log4j2
public class ExercisesController {

    private LogicsForControllers logicsForControllers;
    private ValidateHeaders validateHeaders;

    @Autowired
    public ExercisesController(LogicsForControllers logicsForControllers, ValidateHeaders validateHeaders) {
        this.logicsForControllers = logicsForControllers;
        this.validateHeaders = validateHeaders;
    }

    @GetMapping("/next/{countExercises}/{countAnswer}")
    public Object getNext(@RequestHeader Map<String, String> headers,
                          @PathVariable int countExercises,
                          @PathVariable int countAnswer){
        try {
            validateHeaders.validateTokenAndUser(headers);
        }

        catch (CustomException e){
            return e.getMessage();
        }

        UUID userUUID = UUID.fromString(headers.get("user"));

        return logicsForControllers.nextExercises(userUUID,countExercises,countAnswer);
    }

    //TODO требует проверки на работоспособность и скорее всего это все же создание таски
    @PostMapping("/add_user/{exerciseUUID}")
    public void  addForUser(@RequestHeader Map<String, String> headers,
                            @RequestParam String exerciseUUID) throws CustomException {
        validateHeaders.validateTokenAndUser(headers);

        UUID userUUID = UUID.fromString(headers.get("user"));
        UUID sourseUUID = UUID.fromString(exerciseUUID);
        logicsForControllers.AddTask(sourseUUID,userUUID);
    }

//todo перед боевой выкладкой скрыть данную апи для большинства юзеров
    @PostMapping("/add_user_all/")
    public void  addAllForUser(@RequestHeader Map<String, String> headers) throws Exception {
        validateHeaders.validateTokenAndUser(headers);

        UUID userUUID = UUID.fromString(headers.get("user"));
        logicsForControllers.addTaskAll(userUUID);
    }

    @PostMapping("/create/")
    public void  create(@RequestHeader Map<String, String> headers) throws CustomException {

        logicsForControllers.createExercises();

    }

    @PostMapping("/create/{source_id}")
    public void  create(@RequestHeader Map<String, String> headers,
                        @RequestParam String sourceUUID) throws CustomException {

        logicsForControllers.createExercise(sourceUUID);

    }

}
