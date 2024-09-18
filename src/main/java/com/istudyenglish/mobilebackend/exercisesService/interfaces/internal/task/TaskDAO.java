package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.task;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TaskDAO implements TaskDBPort {

    JdbcTemplate jdbcTemplate;

    TaskMapper taskMapper;

    @Autowired
    public TaskDAO(JdbcTemplate jdbcTemplate, TaskMapper taskMapper, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> getNextOnlyStudy(UUID userUUID, int count) {
        String sql = "select * " +
                "from task " +
                "where user_uuid = " + userUUID.toString() + " " +
                "and status = 'STUDY' " +
                "order by next_repetition " +
                "limit = " + count + ";";

        return jdbcTemplate.query(sql, taskMapper);
    }

    @Override
    public Task getByUserAndExercise(UUID userUUID, UUID exerciseUUID) {
        String sql = "select * " +
                "from task " +
                "where student_uuid = " + userUUID.toString() + " " +
                "and exercise_UUID = " + exerciseUUID.toString() + ";";


        return jdbcTemplate.query(sql, taskMapper).get(0);
    }

    @Override
    public Task genOnUUID(UUID task) {
        String sql = "select * " +
                "from task " +
                "where uuid = " + task.toString() + ";";


        return jdbcTemplate.query(sql, taskMapper).get(0);

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
