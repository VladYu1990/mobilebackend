package com.istudyenglish.mobilebackend.dictionary.interfaces.internal;


import com.istudyenglish.mobilebackend.dictionary.domain.Language;
import com.istudyenglish.mobilebackend.dictionary.domain.PartOfSpeech;
import com.istudyenglish.mobilebackend.dictionary.domain.Source;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class SourceMapper implements RowMapper<Source> {
    @Override
    public Source mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Source.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                textFrom(resultSet.getString("text_from")).
                exampleForFrom(resultSet.getString("from_example")).
                fromLanguage(Language.valueOf(resultSet.getString("from_language"))).
                textTo(resultSet.getString("text_to")).
                exampleForTo(resultSet.getString("to_example")).
                toLanguage(Language.valueOf(resultSet.getString("to_language"))).
                partOfSpeech(PartOfSpeech.valueOf(resultSet.getString("part_of_speech_code"))).
                build();

        //SELECT "uuid", text_from, from_example, from_language, text_to, to_example, to_language, part_of_speech_code
        //FROM public.sources;
    }
}
