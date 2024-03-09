package com.istudyenglish.mobilebackend.adapter.Word;

import com.istudyenglish.mobilebackend.domain.dictionary.Word;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class WordMapper implements RowMapper<Word> {
    @Override
    public Word mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return new Word();
    }
}
