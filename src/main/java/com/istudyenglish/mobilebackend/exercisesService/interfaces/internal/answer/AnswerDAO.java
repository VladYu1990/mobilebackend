package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.answer;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AnswerDAO implements AnswerDBPort {

    JdbcTemplate jdbcTemplate;

    AnswerMapper answerMapper;

    @Autowired
    public AnswerDAO(JdbcTemplate jdbcTemplate, AnswerMapper answerMapper, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.answerMapper = answerMapper;
    }

    @Override
    public List<Answer> get(List<UUID> uuidList) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * " +
                "from text " +
                "where uuid in (");
        for(UUID uuid:uuidList){
            stringBuilder.append(uuid.toString());
            stringBuilder.append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1 );
        stringBuilder.append(");");


        return jdbcTemplate.query(stringBuilder.toString(), answerMapper);
    }
}
