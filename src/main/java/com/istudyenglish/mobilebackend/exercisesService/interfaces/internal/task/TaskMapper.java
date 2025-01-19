package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.task;

import com.istudyenglish.mobilebackend.exercisesService.domain.task.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.task.TaskStatus;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.ExerciseUseCases;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.ExerciseUseCasesImp;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCases;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCasesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class TaskMapper implements RowMapper<Task> {

    private ExerciseUseCases exerciseUseCases;
    private UserUseCases userUseCases;

    @Autowired
    public TaskMapper(ExerciseUseCasesImp exerciseUseCases, UserUseCasesImp userUseCases) {
        this.exerciseUseCases = exerciseUseCases;
        this.userUseCases = userUseCases;
    }


    @Override
    public Task mapRow(ResultSet resultSet, int rowNum) throws SQLException {



        return Task.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                exercise(exerciseUseCases.getOnUUID(UUID.fromString(resultSet.getString("exercise_uuid")))).
                user(userUseCases.getUUID(UUID.fromString(resultSet.getString("user_uuid")))).
                nextRepetition(resultSet.getTimestamp("next_repetition").toInstant()).
                lastRepetition(resultSet.getTimestamp("last_repetition").toInstant()).
                status(TaskStatus.valueOf(resultSet.getString("status"))).
                countRightResponses(resultSet.getInt("count_right_responses")).
                build();
    }
}
