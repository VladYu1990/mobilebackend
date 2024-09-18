package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise;

import com.istudyenglish.mobilebackend.domain.Education.TypesOfDirectionsTranslations;
import com.istudyenglish.mobilebackend.domain.Education.TypesOfExercise;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class ExerciseMapper implements RowMapper<Exercise> {

    @Override
    public Exercise mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Exercise.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                typesOfExercise(TypesOfExercise.valueOf(resultSet.getString("types_of_exercise"))).
                sourceUUID(UUID.fromString(resultSet.getString("source_uuid"))).
                value(resultSet.getString("value")).
                translate(resultSet.getString("translate")).
                typeOfDirectionsTranslations(TypesOfDirectionsTranslations.valueOf(resultSet.getString("type_of_directions_translations"))).
                trueAnswerUUID(UUID.fromString(resultSet.getString("true_answer_uuid"))).
                build();
    }
}
