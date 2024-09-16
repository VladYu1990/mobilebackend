package com.istudyenglish.mobilebackend.userService.interfaces.internal;

import com.istudyenglish.mobilebackend.dictionary.domain.Word;
import com.istudyenglish.mobilebackend.domain.Education.Languages;
import com.istudyenglish.mobilebackend.domain.Education.PartsOfSpeech;
import com.istudyenglish.mobilebackend.userService.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return User.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                login(resultSet.getString("login")).
                password(resultSet.getString("password")).
                phoneNumber(resultSet.getString("phone_number")).
                token(UUID.fromString(resultSet.getString("token"))).
                dateOfDeathToken(resultSet.getTimestamp("date_of_death_token").toInstant())
                .build();
    }
}
