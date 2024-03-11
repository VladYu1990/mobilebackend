package com.istudyenglish.mobilebackend.adapter.exercise;

import com.istudyenglish.mobilebackend.domain.Education.Exercise;
import com.istudyenglish.mobilebackend.port.out.exercise.ExerciseDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ExerciseDAO implements ExerciseDBPort {

    ExerciseMapper exerciseMapper;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ExerciseDAO(ExerciseMapper exerciseMapper, JdbcTemplate jdbcTemplate) {
        this.exerciseMapper = exerciseMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Exercise get(UUID uuid) {
        String sql = "select * from exercises where uuid in ('" + uuid + "');";


        return jdbcTemplate.query(sql,exerciseMapper).get(0);
    }


    public void save(Exercise exercise) {

        String str = "insert into exercises (uuid, exercise_type , source_uuid , value , translation , direction)" +
                " values (''," +
                "'" + exercise.getUuid() + "'," +
                "'" + exercise.getTypesOfExercise() + "'," +
                "'" + exercise.getSourceUUID() + "'," +
                "'" + exercise.getValue() + "'," +
                "'" + exercise.getTranslate() + "'," +
                "'" + exercise.getTypeOfDirectionsTranslations() + "');";

        jdbcTemplate.update(str);
    }

    public void update(Exercise exercise) {
        String str = "update exercises set " +
                "value = '" + exercise.getValue() + "'," +
                "translation = '" + exercise.getTranslate() + "'," +
                "direction = '" + exercise.getTypeOfDirectionsTranslations() + "' " +
                "where uuid = '" + exercise.getUuid() + "');";

        jdbcTemplate.update(str);

    }

    public void delete(Exercise exercise) {
        String str = "delete from exercises " +
                "where uuid = '" + exercise.getUuid() + "');";

        jdbcTemplate.update(str);
    }
}
