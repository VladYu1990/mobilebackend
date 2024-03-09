package com.istudyenglish.mobilebackend.adapter.exercise;

import com.istudyenglish.mobilebackend.domain.Education.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class ExerciseMapper implements RowMapper<Exercise> {

    public Exercise mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Exercise.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                typesOfExercise(TypesOfExercise.valueOf(resultSet.getString("exercise_type"))).
                sourceUUID(UUID.fromString(resultSet.getString("source_uuid"))).
                value(resultSet.getString("value")).
                translate(resultSet.getString("translation")).
                typeOfDirectionsTranslations(TypesOfDirectionsTranslations.valueOf(resultSet.getString("direction"))).
                build();

    }
}
