package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.answer;

import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.TaskStatus;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class AnswerMapper implements RowMapper<Answer> {

    @Override
    public Answer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Answer.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                value(resultSet.getString("value")).
                language(resultSet.getString("language")).
                build();
    }
}
