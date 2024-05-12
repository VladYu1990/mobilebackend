package com.istudyenglish.mobilebackend.adapter;

import com.istudyenglish.mobilebackend.adapter.task.TaskMapper;
import com.istudyenglish.mobilebackend.domain.dictionary.Source;
import com.istudyenglish.mobilebackend.port.out.source.SourceDBPort;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SourceDAO implements SourceDBPort {

    JdbcTemplate jdbcTemplate;



    @Override
    public List<Source> getWord(String string) {
        return null;
    }
}
