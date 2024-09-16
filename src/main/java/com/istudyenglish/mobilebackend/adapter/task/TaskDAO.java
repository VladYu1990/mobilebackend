package com.istudyenglish.mobilebackend.adapter.task;

import com.istudyenglish.mobilebackend.domain.Education.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.port.out.task.TaskDBPort;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

@Component
@Log4j2
public class TaskDAO implements TaskDBPort {

    JdbcTemplate jdbcTemplate;

    TaskMapper taskMapper;

    @Autowired
    public TaskDAO(JdbcTemplate jdbcTemplate, TaskMapper taskMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.taskMapper = taskMapper;
    }

    public Task get(UUID uuid) {
        String sql = "select * from tasks where uuid in ('" + uuid.toString() + "');";
        log.info(sql);
        return jdbcTemplate.query(sql, taskMapper).get(0);
    }

    public Task getByCode(String code){
        String sql = "select * from tasks where uuid in ('" + code + "');";
        log.info(sql);
        return jdbcTemplate.query(sql, taskMapper).get(0);
    }

    public Collection<Task> getNext(Student student, Instant instant) {
        String sql = "select * from tasks\n" +
                "where student_uuid  in ('" + student.getUuid().toString() + "')\n" +
                "and next_repetition <= ('" + instant.toString() + "')\n" +
                "and status not in ('NOT_READY')\n" +
                "order by next_repetition desc;";

        return jdbcTemplate.query(sql, taskMapper);
    }

    public void save(Collection<Task> taskCollection) {

        StringBuilder stringBuilder = new StringBuilder();
        for (Task i : taskCollection) {
            stringBuilder.append(
                    "INSERT INTO tasks VALUES(\n" +
                            "'" + i.getExerciseUUID().toString() + "',\n" +
                            "'" + i.getExerciseUUID().toString() + "',\n" +
                            "'" + i.getStudentUUID() + "',\n" +
                            "'" + i.getNextRepetition().toString() + "',\n" +
                            "'" + i.getLastRepetition().toString() + "',\n" +
                            "'" + i.getStatus().toString() + "',\n" +
                            + i.getCountRightResponses() + ");");
        }
        jdbcTemplate.update(stringBuilder.toString());
    }


    public void update(Collection<Task> taskCollection) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Task i:taskCollection){
            stringBuilder.append(
                    "update tasks " +
                    "set\n" +
                        "exercise_uuid = '" + i.getExerciseUUID().toString() + "',\n" +
                        "student_uuid = '" + i.getStudentUUID() + "',\n" +
                        "next_Repetition = '" + i.getNextRepetition().toString() + "',\n" +
                        "last_Repetition = '" + i.getLastRepetition().toString() + "',\n" +
                        "status = '" + i.getStatus().toString() + "',\n" +
                        "count_Right_Responses = " + i.getCountRightResponses() + " \n" +
                    "where uuid in ('" + i.getUuid() + "');");
        }
        jdbcTemplate.update(stringBuilder.toString());
    }

    public void update(Task task) {
        String sql =
                    "update tasks " +
                    "set\n" +
                    "exercise_uuid = '" + task.getExerciseUUID().toString() + "',\n" +
                    "student_uuid = '" + task.getStudentUUID() + "',\n" +
                    "next_Repetition = '" + task.getNextRepetition().toString() + "',\n" +
                    "last_Repetition = '" + task.getLastRepetition().toString() + "',\n" +
                    "status = '" + task.getStatus().toString() + "',\n" +
                    "count_Right_Responses = " + task.getCountRightResponses() + " \n" +
                    "where uuid in ('" + task.getUuid() + "');";
        jdbcTemplate.update(sql);
    }


}
