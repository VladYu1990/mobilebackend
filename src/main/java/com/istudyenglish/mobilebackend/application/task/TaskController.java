package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.adapter.Response;
import com.istudyenglish.mobilebackend.adapter.Validators.TokenExistStudentValidator;
import com.istudyenglish.mobilebackend.adapter.Validators.TokenValidator;
import com.istudyenglish.mobilebackend.adapter.task.ConvertorToTaskDTO;
import com.istudyenglish.mobilebackend.adapter.task.TaskDTO;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.port.in.task.TaskAdapter;
import com.istudyenglish.mobilebackend.port.in.task.TaskUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks/")
@Log4j2
public class TaskController {

    TaskAdapter taskAdapter;
    ConvertorToTaskDTO convertorToTaskDTO;
    TokenValidator tokenValidator;
    TokenExistStudentValidator tokenExistStudentValidator;

    @Autowired
    public TaskController(TaskAdapter taskAdapter, ConvertorToTaskDTO convertorToTaskDTO, TokenValidator tokenValidator, TokenExistStudentValidator tokenExistStudentValidator) {
        this.taskAdapter = taskAdapter;
        this.convertorToTaskDTO = convertorToTaskDTO;
        this.tokenValidator = tokenValidator;
        this.tokenExistStudentValidator = tokenExistStudentValidator;
    }

    @PostMapping("/create")
    public Response create(@RequestHeader Map<String, String> headers,
                       @RequestParam(defaultValue = "") String time,
                           @RequestParam String words){

        try {
            tokenValidator.check(headers.get("token"));
            tokenExistStudentValidator.check(
                    headers.get("token"),
                    headers.get("student"));

            Instant instant = Instant.now();
            if (!time.isEmpty()) {
                Instant.parse(time);
            }

            List<String> wordsList = new ArrayList<>();
            wordsList = List.of(words.replace(">","").replace("<","").split(","));

            taskAdapter.create(
                    headers.get("token"),
                    wordsList);
            return new Response("ok");

        }
        catch (CustomException customException){
            log.error(customException.getMessage());
            return new Response("error",customException);
        }
    }

    @GetMapping("/validateToken")
    public Response getNext(@RequestHeader Map<String, String> headers,
                            @RequestParam(defaultValue = "") String time,
                            @RequestParam(defaultValue = "1") String amount_tasks,
                            @RequestParam(defaultValue = "4") String amount_answers) {
        try {
            tokenValidator.check(headers.get("token"));
            int amountTasks = Integer.parseInt(amount_tasks);
            int amountAnswers = Integer.parseInt(amount_answers);
            Instant instant;
            if(time.equals("")){
                instant = Instant.now();
            }
            else {
                instant = Instant.parse(time);
            }

            Collection<TaskDTO> taskDTOCollection;
            taskDTOCollection = taskAdapter.getNextTask(
                    headers.get("student"),
                    amountTasks,
                    amountAnswers,
                    instant);

            return new Response("ok",taskDTOCollection);
        }
        catch (CustomException customException){
            return new Response("error",customException);
        }
    }




}
