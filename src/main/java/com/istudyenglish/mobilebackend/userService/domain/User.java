package com.istudyenglish.mobilebackend.userService.domain;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

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
    private UUID token;
    private Instant dateOfDeathToken;

    public User(String login, String password) {
        this.uuid = UUID.randomUUID();
        this.login = login;
        this.password = password;
    }

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

    public void createToken(){
        this.token = UUID.randomUUID();
        //добавляем 30 дней - время жизни токена
        this.dateOfDeathToken = Instant.now().plusSeconds(30*24*60*60);
    };

    public boolean isTokenAlive(){
        if(token == null){
            return false;
        }
        if(Instant.now().isBefore(dateOfDeathToken)){
            return false;
        }
        return true;
    }



}
