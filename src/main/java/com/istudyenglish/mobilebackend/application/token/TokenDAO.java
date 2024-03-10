package com.istudyenglish.mobilebackend.application.token;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.CustomException.CustomExceptionCode;
import com.istudyenglish.mobilebackend.application.user.UserMapper;
import com.istudyenglish.mobilebackend.domain.Autorisation.Token;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.out.token.TokenDBPort;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Component
@Log4j2
public class TokenDAO implements TokenDBPort {
    JdbcTemplate jdbcTemplate;

    TokenMapper tokenMapper;

    @Autowired
    public TokenDAO(JdbcTemplate jdbcTemplate, TokenMapper tokenMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tokenMapper = tokenMapper;
    }

    public void create(Token token) {
        String sql = "INSERT INTO tokens (uuid,date_create,date_death,user_uuid) " +
                "VALUES (" + token.getToken() + ",'" +
                token.getDateCreate() + "','" +
                token.getDateDeath()+ "','" +
                token.getUserUUID() + "');";
        jdbcTemplate.update(sql);
    }

    public Token get(UUID uuid) throws CustomException{
        try{
            String sql = "select * from tokens " +
                    "where uuid in ('" + uuid + "');";

            return jdbcTemplate.query(sql,tokenMapper).get(0);
        }
        catch (Exception e){
            throw new CustomException(CustomExceptionCode.TokenDoNotExist);
        }
    }

    public Token getTokenAliveByUserUUID(UUID userUUID) throws CustomException {
        try{
            String sql = "select * from tokens " +
                    "where user_uuid in ('" + userUUID + "')" +
                    "and date_death >= '" + Instant.now() + "';";

            return jdbcTemplate.query(sql,tokenMapper).get(0);
        }
        catch (Exception e){
            throw new CustomException(CustomExceptionCode.TokenDoNotExist);
        }

    }

    public Collection<Token> getTokensByUserUUID(UUID userUUID) throws CustomException{
        Collection<Token> tokens = new ArrayList<Token>();
        try{
            String sql = "select * from tokens " +
                    "where user_uuid in ('" + userUUID + "')" +
                    "and date_death >= '" + Instant.now() + "';";

            tokens =  jdbcTemplate.query(sql,tokenMapper);
            return tokens;
        }
        catch (Exception e){
            throw new CustomException(CustomExceptionCode.TokenDoNotExist);
        }
    }

    public void killTokens(Collection<Token> tokens) throws CustomException{
        StringBuilder stringBuilder = new StringBuilder();
        for(Token token:tokens){
            stringBuilder.append("update tokens set \n" +
                    "date_death = '" + token.getDateDeath() + "',\n" +
                    "where uuid in ('" + token.getToken() + "');");
        }

        String sql = stringBuilder.toString();
        jdbcTemplate.update(sql);

    }
}
