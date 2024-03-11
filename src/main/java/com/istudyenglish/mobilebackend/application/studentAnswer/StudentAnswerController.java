package com.istudyenglish.mobilebackend.application.studentAnswer;

import com.istudyenglish.mobilebackend.adapter.Response;
import com.istudyenglish.mobilebackend.adapter.Validators.TokenValidator;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.studentAnswer.StudentAnswerAdapterImp;
import com.istudyenglish.mobilebackend.port.in.studentAnswer.StudentAnswerAdapter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/answer/")
@Log4j2
public class StudentAnswerController {


    TokenValidator tokenValidator;
    StudentAnswerAdapter studentAnswerAdapter;

    @Autowired
    public StudentAnswerController(TokenValidator tokenValidator, StudentAnswerAdapterImp studentAnswerAdapter) {
        this.tokenValidator = tokenValidator;
        this.studentAnswerAdapter = studentAnswerAdapter;
    }

    @PostMapping ("/return_reply/{task_code}/answer/{answer_code}")
    public Response returnAnswer(@RequestHeader Map<String, String> headers,
                             @PathVariable String task_code, @PathVariable String answer_code,
                             @RequestParam(defaultValue = "") String time) throws Exception {
        try {
            tokenValidator.check(headers.get("token"));
            Instant instant = Instant.now();
            if (!time.equals("")) {
                Instant.parse(time);
            }

            studentAnswerAdapter.returnAnswer(
                    headers.get("student"),
                    task_code,
                    answer_code,
                    instant);

            return new Response("ok", null);
        } catch (CustomException customException) {
            return new Response("error",customException);
        }
    }


}
