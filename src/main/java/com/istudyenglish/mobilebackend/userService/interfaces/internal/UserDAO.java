package com.istudyenglish.mobilebackend.userService.interfaces.internal;

import com.istudyenglish.mobilebackend.configuration.DataSource;
import com.istudyenglish.mobilebackend.userService.domain.User;
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
    public UserDAO(JdbcTemplate jdbcTemplate, UserMapper userMapper, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }


    @Override
    public void createUser(User user) {
        String sql = "insert into users (uuid,login,password,phone_number,date_create,token,date_of_death_token) " +
                "values('" +
                user.getUuid() + "','" +
                user.getLogin() + "','" +
                user.getPassword() +"','" +
                user.getPhoneNumber() + "','" +
                Instant.now() + "','" +
                user.getToken() + "','" +
                user.getDateOfDeathToken() + "');";

        log.info(sql);

        jdbcTemplate.update(sql);

    }

    @Override
    public User getUUID(UUID userUUID) {
        String sql = "select * " +
                "from user " +
                "where uuid = ('" + userUUID.toString() + "');";

        return jdbcTemplate.query(sql, userMapper).get(0);
    }

    @Override
    public User getUserViaLoginPassword(String login, String password) {
        //TODO возможна sql иньекция, проверить что темплейт от такого умеет защищать
        String sql = "select * " +
                "from users " +
                "where login = ('" + login + "') " +
                "and password = ('" + password + "');";

        return jdbcTemplate.query(sql, userMapper).get(0);
    }

    @Override
    public User getUserViaToken(UUID uuid) {
        String sql = "select * " +
                "from users " +
                "where token = ('" + uuid.toString() + "');";

        return jdbcTemplate.query(sql, userMapper).get(0);
    }

    @Override
    public void createToken(User user) {
        String sql = "update users set " +
                "token = " + user.getToken() + "," +
                "date_of_death_token = " + user.getDateOfDeathToken() + " " +
                "where uuid = " + user.getUuid() + ";";

        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteToken(User user) {
        String sql = "update users set " +
                "token = ''" +
                "date_of_death_token = ''" +
                "where token = " + user.getUuid() + ";";

        jdbcTemplate.update(sql);
    }


}
