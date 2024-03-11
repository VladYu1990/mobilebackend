package com.istudyenglish.mobilebackend.adapter.studentAnswer;

import com.istudyenglish.mobilebackend.adapter.answer.AnswerMapper;
import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.port.out.studentAnswer.StudentAnswerDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class StudentAnswerDAO implements StudentAnswerDBPort {

    JdbcTemplate jdbcTemplate;


    @Autowired
    public StudentAnswerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAnswer(Task task, Answer answer, Instant answerTime,boolean isTrue) {
        String sql = "insert into replies_received values (\n" +
                "'" + task.getUuid() + "',\n" +
                "'" + answer.getUuid() + "',\n" +
                "'" + answerTime.toString() + "',\n" +
                "'" + isTrue + "');";

        jdbcTemplate.update(sql);
    }

}
