package com.istudyenglish.mobilebackend.adapter.answer;

import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.SimilarAnswer;
import com.istudyenglish.mobilebackend.port.out.answer.SimilarAnswersDBport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

@Component
public class SimilarAnswersDAO implements SimilarAnswersDBport {

    JdbcTemplate jdbcTemplate;

    AnswersSimilarMapper answersSimilarMapper;

    @Autowired
    public SimilarAnswersDAO(JdbcTemplate jdbcTemplate, AnswersSimilarMapper answersSimilarMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.answersSimilarMapper = answersSimilarMapper;
    }

    public Collection<SimilarAnswer> getSimilar(Answer answer, int count) {
        String sql = "select  * " +
                "from answer_and_similar " +
                "where answer_true in ('" + answer.getUuid() + "') " +
                "order by similarity_weight desc " +
                "limit " + count + ";";

        return jdbcTemplate.query(sql,answersSimilarMapper);
    }

    public void saveSimilar(Collection<SimilarAnswer> similarAnswerCollection) {
        //todo
    }

    public void update(Collection<SimilarAnswer> similarAnswerCollection) {
        //todo
    }


}
