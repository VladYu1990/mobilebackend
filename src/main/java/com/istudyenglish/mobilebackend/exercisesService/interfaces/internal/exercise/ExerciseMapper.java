package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise;


import com.istudyenglish.mobilebackend.exercisesService.domain.Question;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.TypesOfExercise;
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
                question(Question.builder().
                        uuid(UUID.fromString(resultSet.getString("t1.uuid"))).
                        value(resultSet.getString("t1.value")).
                        language(resultSet.getString("t1.language")).
                        build()).
                answer(Answer.builder().
                        uuid(UUID.fromString(resultSet.getString("t2.uuid"))).
                        value(resultSet.getString("t2.value")).
                        language(resultSet.getString("t2.language")).
                        build())
                .build();
    }
}
