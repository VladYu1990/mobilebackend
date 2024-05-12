package com.istudyenglish.mobilebackend.adapter.Word;

import com.istudyenglish.mobilebackend.DataSource;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.domain.dictionary.Word;
import com.istudyenglish.mobilebackend.port.out.word.WordDBPort;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class WordDAO implements WordDBPort {

    JdbcTemplate jdbcTemplate;

    WordMapper wordMapper;

    @Autowired
    public WordDAO(JdbcTemplate jdbcTemplate, WordMapper wordMapper, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.wordMapper = wordMapper;
    }


    @Override
    public List<Word> get(List<String> valueOrTranslates) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("('" + valueOrTranslates.get(0));

        for(int i =1; i<valueOrTranslates.size();i++){
            stringBuilder.append("','" + i);
        }

        stringBuilder.append("')");



        String sql =
                " select * from word " +
                " where eng_test in " + stringBuilder.toString() +
                " union all " +
                " select * from word " +
                " where rus_text in " + stringBuilder.toString();

        return jdbcTemplate.query(sql, wordMapper);
    }

}
