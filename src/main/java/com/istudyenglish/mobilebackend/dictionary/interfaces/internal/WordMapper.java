package com.istudyenglish.mobilebackend.dictionary.interfaces.internal;


import com.istudyenglish.mobilebackend.dictionary.domain.Language;
import com.istudyenglish.mobilebackend.dictionary.domain.PartOfSpeech;
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
                valueLanguage(Language.valueOf(resultSet.getString("value_language"))).
                translateLanguage(Language.valueOf(resultSet.getString("translate_language"))).
                partOfSpeech(PartOfSpeech.valueOf(resultSet.getString("part_of_speech"))).
                build();
    }
}
