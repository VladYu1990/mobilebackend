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
    public void create(Word word) {

    }

    @Override
    public User get(UUID uuid) throws Exception {
        return null;
    }

    @Override
    public User get(String code) throws Exception {
        return null;
    }

    @Override
    public void update(Word word) {

    }
}
