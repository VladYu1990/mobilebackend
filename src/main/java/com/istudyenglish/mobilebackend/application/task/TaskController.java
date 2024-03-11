package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.adapter.Response;
import com.istudyenglish.mobilebackend.adapter.Validators.TokenValidator;
import com.istudyenglish.mobilebackend.adapter.task.ConvertorToTaskDTO;
import com.istudyenglish.mobilebackend.adapter.task.TaskDTO;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.port.in.task.TaskAdapter;
import com.istudyenglish.mobilebackend.port.in.task.TaskUseCase;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/tasks/")
@Log4j2
public class TaskController {

    TaskAdapter taskAdapter;
    TaskUseCase taskUseCase;
    ConvertorToTaskDTO convertorToTaskDTO;
    TokenValidator tokenValidator;

    @Autowired
    public TaskController(TaskAdapterImpl taskAdapter, ConvertorToTaskDTO convertorToTaskDTO) {
        this.taskAdapter = taskAdapter;
        this.convertorToTaskDTO = convertorToTaskDTO;
    }

    @PostMapping("/create/{exercise_code}")
    public Response create(@RequestHeader Map<String, String> headers,
                       @PathVariable String exercise_code,
                       @RequestParam(defaultValue = "") String time) throws Exception {
        try {
            tokenValidator.check(headers.get("token"));
            Instant instant = Instant.now();
            if (!time.equals("")) {
                Instant.parse(time);
            }
            taskAdapter.create(
                    headers.get("student"),
                    exercise_code,
                    instant);
            return new Response("ok");

        }
        catch (CustomException customException){
            return new Response("error",customException);
        }
    }

    @GetMapping("/getNext/{amount_tasks}/{amount_answers}")
    public Response getNext(@RequestHeader Map<String, String> headers,
                            @PathVariable int amount_tasks,@PathVariable int amount_answers,
                            @RequestParam(defaultValue = "") String time, @RequestParam(defaultValue = "4") String numberOfResponses) throws Exception {
        try {
            tokenValidator.check(headers.get("token"));
            Instant instant = Instant.now();
            if (!time.equals("")) {
                Instant.parse(time);
            }

            Collection<TaskDTO> taskDTOCollection;
            taskDTOCollection = taskAdapter.getNextTask(
                    headers.get("student"),
                    amount_tasks,
                    amount_answers,
                    instant);

            return new Response("ok",taskDTOCollection);
        }
        catch (CustomException customException){
            return new Response("error",customException);
        }
    }




}
