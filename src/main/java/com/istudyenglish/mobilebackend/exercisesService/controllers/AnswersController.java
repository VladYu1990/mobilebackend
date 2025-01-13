package com.istudyenglish.mobilebackend.exercisesService.controllers;

import com.istudyenglish.mobilebackend.CustomException;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.ExerciseUseCases;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.ExerciseUseCasesImp;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.TaskUseCases;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.TaskUseCasesImp;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCases;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCasesImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/answers/")
@Log4j2
public class AnswersController {

    ExerciseUseCases exerciseUseCase;
    TaskUseCases taskUseCases;
    private ValidateHeaders validateHeaders;
    private LogicsForControllers logicsForControllers;


    @Autowired
    public AnswersController(ExerciseUseCasesImp exerciseUseCasesImp, TaskUseCasesImp taskUseCasesImp,ValidateHeaders validateHeaders,LogicsForControllers logicsForControllers) {
        this.exerciseUseCase = exerciseUseCasesImp;
        this.taskUseCases = taskUseCasesImp;
        this.validateHeaders = validateHeaders;
        this.logicsForControllers = logicsForControllers;
    }

    @PostMapping("/give/{answerUUID}/exercise/{exerciseUUID}/time_answer/{timeAnswerInstant}")
    public void give(@RequestHeader Map<String, String> headers,
                     @RequestParam String answerUUID,
                     @RequestParam String exerciseUUID,
                     @RequestParam String timeAnswer) throws CustomException {
        Instant time = Instant.now();

        if(!timeAnswer.isEmpty()){
            time = Timestamp.valueOf(timeAnswer).toInstant();
        }

        validateHeaders.validateTokenAndUser(headers);

        Exercise exercise = exerciseUseCase.getOnUUID(UUID.fromString(exerciseUUID));

        taskUseCases.updateFromAnswer(
                UUID.fromString(headers.get("user")),
                UUID.fromString(exerciseUUID),
                true,
                time);
    }

    @PutMapping("/similar_answers/{code}")
    public void putSimilar(@RequestHeader Map<String, String> headers,
                           @RequestParam String code) throws CustomException {
        validateHeaders.validateTokenAndUser(headers);

        logicsForControllers.putSimilarAnswer(code);
    }

    @PutMapping("/similar_answers/all")
    public void putSimilar(@RequestHeader Map<String, String> headers) throws CustomException {
        validateHeaders.validateTokenAndUser(headers);

        logicsForControllers.putSimilarAnswer();
    }


}
