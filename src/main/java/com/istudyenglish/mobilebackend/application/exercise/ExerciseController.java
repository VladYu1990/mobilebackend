package com.istudyenglish.mobilebackend.application.exercise;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/exercises/")
@Log4j2
public class ExerciseController {





    @PostMapping("/create/")
    public void create(@RequestHeader Map<String, String> headers,
                       @RequestParam String sourceType,
                       @RequestParam String sourceCode,
                       @RequestParam(defaultValue = "") String time) throws Exception {

        Instant instant = Instant.now();
        if(!time.equals("")){
            Instant.parse(time);
        }

    }

    @PostMapping("/delete/{code}")
    public void delete(@RequestHeader Map<String, String> headers,
                       @PathVariable String code) throws Exception {


    }


}
