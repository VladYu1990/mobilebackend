package com.istudyenglish.mobilebackend.application.token;

import com.istudyenglish.mobilebackend.userService.domain.Token;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class TokenMapper implements RowMapper<Token> {


    @Override
    public Token mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Token.builder().
                token(UUID.fromString(resultSet.getString("uuid"))).
                dateCreate(resultSet.getTimestamp("date_create").toInstant()).
                dateDeath(resultSet.getTimestamp("date_death").toInstant()).
                userUUID(UUID.fromString(resultSet.getString("user_uuid"))).
                build();
    }
}
