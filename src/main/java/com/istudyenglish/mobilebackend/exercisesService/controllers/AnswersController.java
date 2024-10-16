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

    UserUseCases userUseCases;
    ExerciseUseCases exerciseUseCase;
    TaskUseCases taskUseCases;


    @Autowired
    public AnswersController(UserUseCasesImp userUseCasesImp, ExerciseUseCasesImp exerciseUseCasesImp, TaskUseCasesImp taskUseCasesImp) {
        this.userUseCases = userUseCasesImp;
        this.exerciseUseCase = exerciseUseCasesImp;
        this.taskUseCases = taskUseCasesImp;
    }

    @GetMapping("/give/{answerUUID}/exercise/{exerciseUUID}/time_answer/{timeAnswerInstant}")
    public void give(@RequestHeader Map<String, String> headers,
                                         @RequestParam String answerUUID,
                                         @RequestParam String exerciseUUID,
                                         @RequestParam String timeAnswer) throws CustomException {
        Instant time = Instant.now();

        if(!timeAnswer.isEmpty()){
            time = Timestamp.valueOf(timeAnswer).toInstant();
        }

        userUseCases.validateToken(
                headers.get("token"),
                headers.get("user"));

        Exercise exercise = exerciseUseCase.getOnUUID(UUID.fromString(exerciseUUID));
        boolean isTrue = exercise.checkAnswer(UUID.fromString(answerUUID));

        taskUseCases.updateFromAnswer(
                UUID.fromString(headers.get("user")),
                UUID.fromString(exerciseUUID),
                isTrue,
                time);
    }

}
