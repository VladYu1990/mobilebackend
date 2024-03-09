package com.istudyenglish.mobilebackend.adapter.answer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class AnswerMapper implements RowMapper<Answer> {

    public Answer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Answer.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                code(resultSet.getString("code")).
                value(resultSet.getString("value")).
                soundURL(resultSet.getString("sound_url")).
                build();
    }
}
