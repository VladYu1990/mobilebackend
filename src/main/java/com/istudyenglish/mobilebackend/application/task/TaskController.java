package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.adapter.Response;
import com.istudyenglish.mobilebackend.adapter.task.CreatorTaskDTO;
import com.istudyenglish.mobilebackend.port.in.task.TaskAdapter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/tasks/")
@Log4j2
public class TaskController {

    TaskAdapter taskAdapter;
    CreatorTaskDTO creatorTaskDTO;

    @Autowired
    public TaskController(TaskAdapterImpl taskAdapter,CreatorTaskDTO creatorTaskDTO) {
        this.taskAdapter = taskAdapter;
        this.creatorTaskDTO = creatorTaskDTO;
    }

    @PostMapping("/create/{exercise_code}")
    public void create(@RequestHeader Map<String, String> headers,
                       @PathVariable String exercise_code,
                       @RequestParam(defaultValue = "") String answerTime) throws Exception {

        Instant instant = Instant.now();
        if(!answerTime.equals("")){
            Instant.parse(answerTime);
        }

        taskAdapter.create(
                headers.get("token"),
                headers.get("student_code"),
                exercise_code,
                instant);
    }

    @GetMapping("/getNext/{amount_tasks}")
    public Response getNext(@RequestHeader Map<String, String> headers,
                            @PathVariable int amount_tasks,
                            @RequestParam(defaultValue = "") String time, @RequestParam(defaultValue = "4") String numberOfResponses) throws Exception {
        Instant instant = Instant.now();
        if(!time.equals("")){
            Instant.parse(time);
        }
        Collection<Task> taskCollection =
                taskAdapter.getNextTask(
                        headers.get("token"),
                        headers.get("student_code"),
                        amount_tasks,
                        instant);

        return new Response("ok",
                creatorTaskDTO.convert(
                        taskCollection,
                        Integer.parseInt(numberOfResponses)));
    }


    @PostMapping ("/return_reply/{task_code}/answer/{answer_code}")
    public Response returnAnswer(@RequestHeader Map<String, String> headers,
                             @PathVariable String task_code, @PathVariable String answer_code,
                             @RequestParam(defaultValue = "") String time) throws Exception {

        log.info("return_reply");
        answer_code = "c19fb970-5d3a-4b37-9248-784353b507a1";
        log.info(task_code + "///"+ answer_code);
        Instant instant = Instant.now();
        if(!time.equals("")){
            Instant.parse(time);
        }
        taskAdapter.returnAnswer(
                headers.get("token"),
                headers.get("student_code"),
                task_code,
                answer_code,
                instant);

        return new Response("ok",null);
    }

    @GetMapping("/check_answer/{task_code}/answer/{answer_code}")
    public boolean checkAnswer(@RequestHeader Map<String, String> headers,
                             @PathVariable String task_code, @PathVariable String answer_code,
                             @RequestParam(defaultValue = "") String answerTime) throws Exception {


        Instant instant = Instant.now();
        if(!answerTime.equals("")){
            Instant.parse(answerTime);
        }
        return taskAdapter.checkAnswer(
                headers.get("token"),
                headers.get("studentCode"),
                task_code,
                answer_code);
    }


}
