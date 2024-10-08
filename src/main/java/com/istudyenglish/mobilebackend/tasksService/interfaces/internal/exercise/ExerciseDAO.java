package com.istudyenglish.mobilebackend.tasksService.interfaces.internal.exercise;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.tasksService.domain.exercise.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ExerciseDAO implements ExerciseDBPort {

    JdbcTemplate jdbcTemplate;

    ExerciseMapper exerciseMapper;

    @Autowired
    public ExerciseDAO(JdbcTemplate jdbcTemplate, ExerciseMapper exerciseMapper, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public Exercise genOnUUID(UUID exerciseUUID) {
        String sql = "select * " +
                "from exercise " +
                "where uuid = " + exerciseUUID.toString() + ";";

        return jdbcTemplate.query(sql, exerciseMapper).get(0);

    }

    @Override
    public void create(Task task) {
        String sql = "insert into task(uuid,exercise_uuid,user_uuid,next_repetition,last_repetition,status,count_right_responses) " +
                "values(" +
                task.getUuid() + "," +
                task.getExerciseUUID() + "," +
                task.getUserUUID() + "," +
                task.getNextRepetition() + "," +
                task.getLastRepetition() + "," +
                task.getStatus().toString() + "," +
                task.getCountRightResponses() + ",);";

        jdbcTemplate.update(sql);

    }

    @Override
    public void update(Task task) {
        String sql = "update task set " +
                "next_repetition = " + task.getNextRepetition() + "," +
                "last_repetition = " + task.getLastRepetition() + "," +
                "status = " + task.getStatus().toString() + "," +
                "count_right_responses = " + task.getCountRightResponses() + " " +
                "where uuid = " + task.getUuid() + ";";

        jdbcTemplate.update(sql);
    }

}
