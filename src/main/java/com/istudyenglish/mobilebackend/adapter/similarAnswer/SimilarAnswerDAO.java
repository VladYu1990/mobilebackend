package com.istudyenglish.mobilebackend.adapter.similarAnswer;

import com.istudyenglish.mobilebackend.port.out.similarAnswer.SimilarAnswersDBport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SimilarAnswerDAO implements SimilarAnswersDBport {

    JdbcTemplate jdbcTemplate;

    SimilarAnswerMapper similarAnswerMapper;

    @Autowired
    public SimilarAnswerDAO(JdbcTemplate jdbcTemplate, SimilarAnswerMapper similarAnswerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.similarAnswerMapper = similarAnswerMapper;
    }

    public Collection<SimilarAnswer> getSimilar(Answer answer, int count) {
        String sql = "select  * " +
                "from answer_and_similar " +
                "where answer_true in ('" + answer.getUuid() + "') " +
                "order by similarity_weight desc " +
                "limit " + count + ";";

        return jdbcTemplate.query(sql, similarAnswerMapper);
    }

    public void saveSimilar(Collection<SimilarAnswer> similarAnswerCollection) {
        //todo
    }

    public void update(Collection<SimilarAnswer> similarAnswerCollection) {
        //todo
    }


}
