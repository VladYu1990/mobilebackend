package com.istudyenglish.mobilebackend.application.token;

import com.istudyenglish.mobilebackend.domain.Autorisation.Token;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

@Component
public class TokenMapper implements RowMapper<Token> {


    @Override
    public Token mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return Token.builder().
                token(UUID.fromString(resultSet.getString("uuid"))).
                dateCreate(Instant.parse(resultSet.getString("date_create"))).
                dateDeath(Instant.parse(resultSet.getString("date_death"))).
                userUUID(UUID.fromString(resultSet.getString("user_uuid"))).
                build();
    }
}
