package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.exercise;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public List<Exercise> getAll() {
        String sql = "select *" +
                "from exercises";

        return jdbcTemplate.query(sql, exerciseMapper);
    }

    @Override
    public void save(Exercise exercise) {
        try{
            String sql = "INSERT INTO public.exercises\n" +
                    "(\"uuid\", types_of_exercise, source_uuid, question, answer)\n" +
                    "VALUES('" +
                    exercise.getUuid() + "','" +
                    exercise.getTypesOfExercise().toString() + "','" +
                    exercise.getSourceUUID().toString() + "','" +
                    exercise.getQuestion().getUuid().toString() + "','" +
                    exercise.getAnswer().getUuid().toString() + "');";

            jdbcTemplate.update(sql);
        }
        catch (DataAccessException ignored){}
    }

    @Override
    public List<Exercise> genOnSourceUUID(UUID sourceUUID) {
        String sql = "select * " +
                "from exercises " +
                "where source_uuid = " + sourceUUID.toString() + ";";

        return jdbcTemplate.query(sql, exerciseMapper);
    }
}
