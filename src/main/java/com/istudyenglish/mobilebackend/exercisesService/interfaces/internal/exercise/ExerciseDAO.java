package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        List<Exercise> exerciseList = new ArrayList<>();
        if(!exerciseUUIDsList.isEmpty()) {

            StringBuilder stringBuilder = new StringBuilder("('" + exerciseUUIDsList.remove(0).toString() + "'");

            for (UUID uuid : exerciseUUIDsList) {
                stringBuilder.append(",'" + uuid.toString() + "'");

            }
            String sql = "select *" +
                    "from exercises " +
                    "where uuid in  " + stringBuilder.toString() + ")";


            exerciseList = jdbcTemplate.query(sql, exerciseMapper);
        }
        return exerciseList;

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
