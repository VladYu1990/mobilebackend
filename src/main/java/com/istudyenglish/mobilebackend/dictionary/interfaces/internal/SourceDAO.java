package com.istudyenglish.mobilebackend.dictionary.interfaces.internal;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.dictionary.domain.Source;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class SourceDAO implements SourceDBPort {

    JdbcTemplate jdbcTemplate;

    SourceMapper sourceMapper;

    @Autowired
    public SourceDAO(JdbcTemplate jdbcTemplate, SourceMapper sourceMapper, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.sourceMapper = sourceMapper;
    }


    @Override
    public Source getUUID(UUID uuid){
        String sql =
                " select * " +
                "from sources " +
                "where uuid=" + uuid.toString();

        return (Source) jdbcTemplate.query(sql, sourceMapper);
    }

    @Override
    public List<Source> getAll() {
        String sql =
                " select * " +
                        "from sources;";

        return jdbcTemplate.query(sql, sourceMapper);
    }

    @Override
    public void create(Source source) {
        String sql = "INSERT INTO public.sources\n" +
                "(\"uuid\", text_from, from_example, from_language, text_to, to_example, to_language, part_of_speech_code)\n" +
                "VALUES('" + source.getUuid() + "','" +
                source.getTextFrom() + "','" +
                source.getExampleForFrom() + "','" +
                source.getFromLanguage() + "','" +
                source.getTextTo() + "','" +
                source.getExampleForTo() + "','"+
                source.getToLanguage() + "','" +
                source.getPartOfSpeech() + "');";

        jdbcTemplate.update(sql);
    }
}
