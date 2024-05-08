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
        String sql = "INSERT INTO users (uuid,login,password,phone_number) " +
                "VALUES ('" + user.getUuid() + "','" + user.getLogin() + "','" + user.getPassword()+ "','" +
                user.getPhoneNumber() + "');";
        jdbcTemplate.update(sql);
        log.info(sql);
    }

    public User getByUUID(UUID uuid) throws NullPointerException{
        String sql = "select * from users " +
                    "where uuid in ('" + uuid.toString()+ "');";

        return jdbcTemplate.query(sql,userMapper).get(0);
    }

    public User getByLoginAndPassword(String login,String password) throws NullPointerException{
        String sql = "select * from users " +
                    "where login in ('" + login + "')" +
                "and password in ('" + password + "');";

        log.info(sql);
        return jdbcTemplate.query(sql,userMapper).get(0);

    }


    public void update(User user) {
        String sql = "update users set \n" +
                "password = '" + user.getPassword() + "',\n" +
                "phone_number = '" + user.getPhoneNumber() + "',\n" +
                "where uuid in ('" + user.getUuid() + "');";

                jdbcTemplate.update(sql);
    }
}
