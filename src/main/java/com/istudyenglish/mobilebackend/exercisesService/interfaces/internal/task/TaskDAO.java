package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.task;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.exercisesService.domain.task.Task;
import com.istudyenglish.mobilebackend.userService.domain.User;
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
    public List<Task> getNextOnlyStudy(User user, int count) {

        String sql = "select * " +
                "from tasks " +
                "where user_uuid in ('" + user.getUuid().toString() + "') " +
                "and status in ('STUDY') " +
                "order by next_repetition " +
                "limit  " + count + ";";

        return jdbcTemplate.query(sql, taskMapper);



    }

    @Override
    public Task getByUserAndExercise(UUID userUUID, UUID exerciseUUID) {
        String sql = "select * " +
                "from tasks " +
                "where student_uuid in ('" + userUUID.toString() + "') " +
                "and exercise_UUID in ('" + exerciseUUID.toString() + "');";


        return jdbcTemplate.query(sql, taskMapper).get(0);
    }

    @Override
    public Task genOnUUID(UUID task) {
        String sql = "select * " +
                "from tasks " +
                "where uuid = " + task.toString() + ";";


        return jdbcTemplate.query(sql, taskMapper).get(0);

    }

    @Override
    public void create(List<Task> taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Task task: taskList) {
            stringBuilder.append("insert into tasks(uuid,exercise_uuid,user_uuid,next_repetition,last_repetition,status,count_right_responses) " +
                    "values('" +
                    task.getUuid().toString() + "','" +
                    task.getExercise().getUuid().toString() + "','" +
                    task.getUser().getUuid().toString() + "','" +
                    task.getNextRepetition() + "','" +
                    task.getLastRepetition() + "','" +
                    task.getStatus().toString() + "','" +
                    task.getCountRightResponses() + "');\n");
        }

        jdbcTemplate.update(stringBuilder.toString());
    }

    @Override
    public void update(Task task) {
        String sql = "update tasks set " +
                "next_repetition = " + task.getNextRepetition() + "," +
                "last_repetition = " + task.getLastRepetition() + "," +
                "status = " + task.getStatus().toString() + "," +
                "count_right_responses = " + task.getCountRightResponses() + " " +
                "where uuid = " + task.getUuid() + ";";

        jdbcTemplate.update(sql);
    }

}
