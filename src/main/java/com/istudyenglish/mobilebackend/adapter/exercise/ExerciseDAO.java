package com.istudyenglish.mobilebackend.adapter.exercise;

import com.istudyenglish.mobilebackend.tasksService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.port.out.exercise.ExerciseDBPort;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
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

    public List<Exercise> get(List<String> strings) {
       //todo
        return null;
    }

    @Override
    public List<Exercise> getFromWordUUID(List<String> collection) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("('" + collection.get(0));

        for(int i =1; i<collection.size();i++){
            stringBuilder.append("','" + i);
        }

        stringBuilder.append("')");

        String sql = "select * from exercises " +
                "where source_uuid in " + stringBuilder.toString();

        return jdbcTemplate.query(sql,exerciseMapper);
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
