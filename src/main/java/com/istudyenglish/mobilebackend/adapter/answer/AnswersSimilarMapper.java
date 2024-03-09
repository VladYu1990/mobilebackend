package com.istudyenglish.mobilebackend.adapter.answer;

import com.istudyenglish.mobilebackend.domain.Education.SimilarAnswer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class AnswersSimilarMapper implements RowMapper<SimilarAnswer> {

    public SimilarAnswer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return new SimilarAnswer(
                UUID.fromString(resultSet.getString("answer_true")),
                UUID.fromString(resultSet.getString("answer_similar_uuid")),
                resultSet.getDouble("similarity_weight")
                        );
    }
}
