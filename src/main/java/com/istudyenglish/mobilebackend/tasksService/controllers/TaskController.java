package com.istudyenglish.mobilebackend.tasksService.controllers;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.tasksService.adapters.TaskForView;
import com.istudyenglish.mobilebackend.tasksService.adapters.TaskForViewAdapter;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.tasksService.interfaces.external.TaskUseCases;
import com.istudyenglish.mobilebackend.tasksService.interfaces.external.TaskUseCasesImp;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCases;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCasesImp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/tasks/")
@Log4j2
public class TaskController {

    UserUseCases userUseCases;
    TaskUseCases taskUseCases;
    TaskForViewAdapter taskForViewAdapter;

    @Autowired
    public TaskController(UserUseCasesImp userUseCasesImp, TaskUseCasesImp taskUseCasesImp,TaskForViewAdapter taskForViewAdapter) {
        this.userUseCases = userUseCasesImp;
        this.taskUseCases = taskUseCasesImp;
        this.taskForViewAdapter = taskForViewAdapter;
    }

    @GetMapping("/next/{count}")
    public List<TaskForView> getNext(@RequestHeader Map<String, String> headers,
                                     @RequestParam int count) throws CustomException {

        userUseCases.validateToken(
                headers.get("token"),
                headers.get("user"));

        List<Task> taskList = taskUseCases.getNext(
                UUID.fromString(headers.get("user")),count);

        return taskForViewAdapter.getTaskForViews(taskList);
    }

    @GetMapping("/id/{uuid}")
    public TaskForView getNext(@RequestHeader Map<String, String> headers,
                              @RequestParam String uuid) throws CustomException {

        userUseCases.validateToken(
                headers.get("token"),
                headers.get("user"));

        Task task = taskUseCases.getOnUUID(
                UUID.fromString(headers.get("user")),
                UUID.fromString(uuid));

        return  taskForViewAdapter.getTaskForView(task);
    }

    @PostMapping("/task/{uuidTask}/answer/{uuidAnswer}/time_answer/{time}")
    public void giveAnswer(@RequestHeader Map<String, String> headers,
                        @RequestParam String uuidTask,
                        @RequestParam String uuidAnswer,
                        @RequestParam String time) throws CustomException {


        userUseCases.validateToken(
                headers.get("token"),
                headers.get("user"));

        Instant timeAnswer = Instant.now();

        if(!time.isEmpty()){
            timeAnswer = Timestamp.valueOf(time).toInstant();
        }

        taskUseCases.giveAnswer(
                UUID.fromString(headers.get("user")),
                UUID.fromString(uuidTask),
                UUID.fromString(uuidAnswer),
                timeAnswer);
    }
}
