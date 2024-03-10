package com.istudyenglish.mobilebackend.domain.Autorisation;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.CustomException.CustomExceptionCode;
import lombok.*;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class User {
    /**
     * UUID пользователя
     */
    private UUID uuid;
    /**
     * Логин пароля
     */
    private String login;
    /**
     * Пароль пользователя
     */
    private String password;
    /**
     * Номер телефона пользователя
     */
    private String phoneNumber;

    public User(String login, String password, String phoneNumber) {
        this.uuid = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void setNewPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String password){
        if(this.password.equals(password)){
            return true;
        }
        else {
            return false;
        }
    }



}
