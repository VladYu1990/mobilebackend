package com.istudyenglish.mobilebackend.adapter.Word;

import com.istudyenglish.mobilebackend.domain.Education.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@Log4j2
public class RequestForNeTasksdDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public RequestForNeTasksdDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(List<String> words, Student student) {

        StringBuilder stringBuilder = new StringBuilder();

        for(String str:words){
            stringBuilder.append("INSERT INTO tasks VALUES('" + student.getUuid() + "','" + str + "');");
        }

        jdbcTemplate.update(stringBuilder.toString());
    }

}
