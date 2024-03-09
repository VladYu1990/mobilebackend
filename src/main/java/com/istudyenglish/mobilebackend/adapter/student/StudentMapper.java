package com.istudyenglish.mobilebackend.adapter.student;

import com.istudyenglish.mobilebackend.domain.Education.Languages;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Student.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                code(resultSet.getString("code")).
                userUuid(UUID.fromString(resultSet.getString("user_uuid"))).
                studentFullName(resultSet.getString("student_full_name")).
                nativeLanguages(Languages.valueOf(resultSet.getString("native_languages"))).
                languagesForStudy(Languages.valueOf(resultSet.getString("languages_for_study"))).
                dateCreated(resultSet.getTimestamp("date_created").toInstant()).
                build();
    }
}
