package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.answer;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.SimilarAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AnswerDAO implements AnswerDBPort {

    JdbcTemplate jdbcTemplate;
    AnswerMapper answerMapper;

    @Autowired
    public AnswerDAO(JdbcTemplate jdbcTemplate, AnswerMapper answerMapper, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.answerMapper = answerMapper;
    }

    @Override
    public List<Answer> get(List<UUID> uuidList) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * " +
                "from answers " +
                "where uuid in (");
        for(UUID uuid:uuidList){
            stringBuilder.append(uuid.toString());
            stringBuilder.append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1 );
        stringBuilder.append(");");


        return jdbcTemplate.query(stringBuilder.toString(), answerMapper);
    }

    @Override
    public Answer get(UUID answerUUID) {
        String sql = "select * " +
                "from answers " +
                "where uuid in ('" + answerUUID.toString() + "');";
        return jdbcTemplate.query(sql, answerMapper).get(0);
    }

    @Override
    public Answer get(String value) {
        String sql = "select * " +
                "from answers " +
                "where value in ('" + value + "');";
        return jdbcTemplate.query(sql, answerMapper).get(0);
    }

    @Override
    public List<Answer> getSimilarAnswers(Answer answer, int count) {
        String sql = "select a.*,s.similarity_coefficient " +
                "from similar_text s,answers a " +
                "where a.uuid = s.answer2 " +
                "and answer1 in ('" + answer.getUuid() + "') " +
                "order by similarity_coefficient desc " +
                "limit " + count + ";";
        return  jdbcTemplate.query(sql, answerMapper);
    }

    @Override
    public List<Answer> getAll() {
        String sql = "select * from answers";
        return  jdbcTemplate.query(sql, answerMapper);
    }

    @Override
    public void saveSimilarAnswers(SimilarAnswer similarAnswer) {
        String sql = "INSERT INTO similar_text (answer1,answer2,similarity_coefficient) " +
                "VALUES ("+
                "'" + similarAnswer.getAnswer().getUuid().toString() +  "'," +
                "'" + similarAnswer.getSimilarAnswer().getUuid().toString() +  "'," +
                + similarAnswer.getSimilarityCoefficient() + ");";

        jdbcTemplate.update(sql);

    }

    @Override
    public void updateSimilarAnswers(SimilarAnswer similarAnswer) {
        String sql = "UPDATE similar_text set similarity_coefficient = " + similarAnswer.getSimilarityCoefficient() +
                " where answer1 in ('" + similarAnswer.getAnswer().getUuid().toString() + "')" +
                " and answer2 in ('" + similarAnswer.getSimilarAnswer().getUuid().toString() + "');";

        jdbcTemplate.update(sql);

    }
}
