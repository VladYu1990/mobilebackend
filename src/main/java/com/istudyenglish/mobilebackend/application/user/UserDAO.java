package com.istudyenglish.mobilebackend.application.user;

import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.port.out.User.UserDBPort;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@Log4j2
public class UserDAO implements UserDBPort {

    JdbcTemplate jdbcTemplate;

    UserMapper userMapper;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    public void create(User user) {
//todo
    }

    public User get(UUID uuid) throws Exception{
        try {
            String sql = "select * from users " +
                    "where uuid in ('" + uuid.toString()+ "');";

            return jdbcTemplate.query(sql,userMapper).get(0);
        }
        catch (NullPointerException npe){
            log.error("User UUID" + uuid.toString() + " isn't valid");
            throw npe;
        }
    }

    public User getLogin(String login) throws Exception{
        try {
            String sql = "select * from users " +
                    "where login in ('" + login + "');";

            return jdbcTemplate.query(sql,userMapper).get(0);
        }
        catch (NullPointerException npe){
            log.error("User Login" + login + " isn't valid");
            throw npe;
        }
    }

    public User get(String tokenStr) throws Exception {
        try {
            String sql = "select * from users " +
                    "where token in ('" + tokenStr + "')" +
                    "and date_death_token >= ('" + Instant.now() + "');";

            return jdbcTemplate.query(sql,userMapper).get(0);
        }
        catch (NullPointerException npe){
            log.error("Token " + tokenStr + " isn't valid");
            throw npe;
        }
    }

    public User get(String login,String password){
        String sql = "select * from users " +
                    "where login in ('" + login + "')" +
                    "and password in ('" + password + "');";
            return jdbcTemplate.query(sql,userMapper).get(0);

    }

    public void update(User user) {
        String sql = "update users set \n" +
                "password = '" + user.getPassword() + "',\n" +
                "token = '" + user.getToken() + "',\n" +
                "date_create_token = '" + user.getDateCreateToken() + "',\n" +
                "date_death_token = '" + user.getDateDeathToken() + "',\n" +
                "active = '" + user.isActive() + "'n" +
                "where uuid in ('" + user.getUuid() + "');";

                jdbcTemplate.update(sql);
    }
}
