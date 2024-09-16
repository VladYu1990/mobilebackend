package com.istudyenglish.mobilebackend.dictionary.interfaces.internal;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.dictionary.domain.Word;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
    public Word getUUID(UUID uuid){
        String sql =
                " select * " +
                "from word " +
                "where uuid=" + uuid.toString();

        return (Word) jdbcTemplate.query(sql, wordMapper);
    }

}
