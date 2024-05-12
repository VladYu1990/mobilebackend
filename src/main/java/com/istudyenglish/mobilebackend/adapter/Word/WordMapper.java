package com.istudyenglish.mobilebackend.adapter.Word;

import com.istudyenglish.mobilebackend.domain.Education.PartsOfSpeech;
import com.istudyenglish.mobilebackend.domain.dictionary.Word;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class WordMapper implements RowMapper<Word> {
    @Override
    public Word mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Word.builder().
                id(resultSet.getInt("id")).
                eng(resultSet.getString("eng_text")).
                rus(resultSet.getString("rus_text")).
                partsOfSpeech(PartsOfSpeech.valueOf(resultSet.getString("part_of_speech"))).
                build();
    }
}
