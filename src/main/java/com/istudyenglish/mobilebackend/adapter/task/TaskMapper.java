package com.istudyenglish.mobilebackend.adapter.task;

import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.tasksService.domain.TaskStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Task.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                exerciseUUID(UUID.fromString(resultSet.getString("exercise_uuid"))).
                studentUUID(UUID.fromString(resultSet.getString("student_uuid"))).
                nextRepetition(resultSet.getTimestamp("next_Repetition").toInstant()).
                lastRepetition(resultSet.getTimestamp("last_Repetition").toInstant()).
                status(TaskStatus.valueOf(resultSet.getString("status"))).
                countRightResponses(resultSet.getInt("count_Right_Responses")).
                build();
    }
}
