package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.text;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.exercisesService.domain.Question;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TextDAO implements TextDBPort {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TextDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Question question) {
        String sql = "insert into text(uuid,value,language) " +
                "values(" +
                question.getUuid() + "," +
                question.getValue() + "," +
                question.getLanguage() + ";";
        jdbcTemplate.update(sql);

    }

    @Override
    public void create(Answer answer) {
        String sql = "insert into text(uuid,value,language) " +
                "values(" +
                answer.getUuid() + "," +
                answer.getValue() + "," +
                answer.getLanguage() + ";";
        jdbcTemplate.update(sql);
    }

    @Override
    public void update(Question question) {
    //todo
    }

    @Override
    public void update(Answer answer) {
     //todo
    }

}
