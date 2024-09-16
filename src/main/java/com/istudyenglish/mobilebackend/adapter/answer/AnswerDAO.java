package com.istudyenglish.mobilebackend.adapter.answer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.port.out.answer.AnswerDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Component
public class AnswerDAO implements AnswerDBPort {

    JdbcTemplate jdbcTemplate;

    AnswerMapper answerMapper;


    @Autowired
    public AnswerDAO(JdbcTemplate jdbcTemplate, AnswerMapper answerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.answerMapper = answerMapper;
    }

    public Answer getByUUID(UUID uuid) {
        String sql = "select * from answers where uuid in ('" + uuid.toString() + "');";
        return jdbcTemplate.query(sql,answerMapper).get(0);
    }

    public Answer getByString(String value){
        String sql = "select * from answers where value in ('" + value + "');";
        return jdbcTemplate.query(sql,answerMapper).get(0);
    }



    public void update(Collection<Task> taskCollection, Instant instant) {

    }

    public void delete(Collection<Task> taskCollection, Instant instant) {

    }

    public void saveAnswer(Task task, Answer answer, Instant answerTime,boolean isTrue) {
        String sql = "insert into replies_received values (\n" +
                "'" + task.getUuid() + "',\n" +
                "'" + answer.getUuid() + "',\n" +
                "'" + answerTime.toString() + "',\n" +
                "'" + isTrue + "');";

        jdbcTemplate.update(sql);
    }

    public  Answer getByCode(String code){
        String sql = "select * from answers where uuid in ('" + code + "');";
        return jdbcTemplate.query(sql,answerMapper).get(0);
    }
}
