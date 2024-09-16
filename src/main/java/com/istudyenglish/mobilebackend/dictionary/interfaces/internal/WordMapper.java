package com.istudyenglish.mobilebackend.dictionary.interfaces.internal;

import com.istudyenglish.mobilebackend.domain.Education.Languages;
import com.istudyenglish.mobilebackend.domain.Education.PartsOfSpeech;
import com.istudyenglish.mobilebackend.dictionary.domain.Word;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class WordMapper implements RowMapper<Word> {
    @Override
    public Word mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Word.builder().
                uuid(UUID.fromString(resultSet.getString("id"))).
                value(resultSet.getString("value")).
                translate(resultSet.getString("translate")).
                valueLanguage(Languages.valueOf(resultSet.getString("value_language"))).
                translateLanguage(Languages.valueOf(resultSet.getString("translate_language"))).
                partOfSpeech(PartsOfSpeech.valueOf(resultSet.getString("part_of_speech"))).
                build();
    }
}
