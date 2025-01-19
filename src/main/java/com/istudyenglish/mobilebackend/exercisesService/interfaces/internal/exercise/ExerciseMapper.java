package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise;


import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.TypesOfExercise;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.AnswerUseCases;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.AnswerUseCasesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class ExerciseMapper implements RowMapper<Exercise> {
    private AnswerUseCases answerUseCases;

    @Autowired
    public ExerciseMapper(AnswerUseCasesImp answerUseCases) {
        this.answerUseCases = answerUseCases;
    }

    @Override
    public Exercise mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Exercise.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                typesOfExercise(TypesOfExercise.valueOf(resultSet.getString("types_of_exercise"))).
                sourceUUID(UUID.fromString(resultSet.getString("source_uuid"))).
                question(answerUseCases.getByUUID(UUID.fromString(resultSet.getString("question")))).
                answer(answerUseCases.getByUUID(UUID.fromString(resultSet.getString("answer")))).
                build();
    }
}
