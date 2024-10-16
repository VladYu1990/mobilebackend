package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
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
    public List<Exercise> genOnUUIDs(List<UUID> exerciseUUIDsList) {

        StringBuilder stringBuilder = new StringBuilder();
        //todo доработать блок селекта и маппер
        stringBuilder.append("select e.*,t1.*,t2.* " +
                "from exercise as e,text as t1,text as t2 " +
                "where e.question_uuid = t1.uuid " +
                "and e.answer_uuid =t2.uuid " +
                "and uuid in (");
        for(UUID uuid: exerciseUUIDsList){
            stringBuilder.append(uuid.toString());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() -1);
        stringBuilder.append(");");

        return jdbcTemplate.query(stringBuilder.toString(), exerciseMapper);

    }

    @Override
    public void create(Exercise exercise) {
        //todo


    }

    @Override
    public void update(Exercise exercise) {

    }

    @Override
    public List<Exercise> genOnSourceUUID(UUID sourceUUID) {
        String sql = "select * " +
                "from exercises " +
                "where source_uuid = " + sourceUUID.toString() + ";";

        return jdbcTemplate.query(sql, exerciseMapper);
    }


}
