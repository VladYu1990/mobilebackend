package com.istudyenglish.mobilebackend.tasksService.interfaces.internal.exercise;

import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.tasksService.domain.TaskStatus;
import com.istudyenglish.mobilebackend.tasksService.domain.exercise.Exercise;
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
                exerciseUUID(UUID.fromString(resultSet.getString("exercise_uuid"))).
                userUUID(UUID.fromString(resultSet.getString("user_uuid"))).
                nextRepetition(resultSet.getTimestamp("next_repetition").toInstant()).
                lastRepetition(resultSet.getTimestamp("last_repetition").toInstant()).
                status(TaskStatus.valueOf(resultSet.getString("status"))).
                countRightResponses(resultSet.getInt("count_right_responses")).
                build();
    }
}
