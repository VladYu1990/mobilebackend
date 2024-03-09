package com.istudyenglish.mobilebackend.application.user;

import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return User.builder().
                uuid(UUID.fromString(resultSet.getString("uuid"))).
                login(resultSet.getString("login")).
                password(resultSet.getString("password")).
                token(UUID.fromString(resultSet.getString("token"))).
                dateCreateToken(resultSet.getTimestamp("date_create_token").toInstant()).
                dateDeathToken(resultSet.getTimestamp("date_death_token").toInstant()).
                build();
    }
}
