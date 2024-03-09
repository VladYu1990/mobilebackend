package com.istudyenglish.mobilebackend.domain.Autorisation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

@AllArgsConstructor
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
     * Токен пользователя
     */
    private String phoneNumber;
    private UUID token;
    /**
     * Дата/время создания токена
     */
    private Instant dateCreateToken;
    /**
     * Дата/время смерти токена
     */
    private Instant dateDeathToken;
    /**
     * Активный или заблокированный юзер
     */
    private boolean active;

    public User(String login, String password, String phoneNumber) {
        this.uuid = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        refreshToken();
    }

    public void setNewPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean checkToken(){
        if(this.dateCreateToken.isAfter(Instant.now())){
            return true;
        }
        else {
            return false;
        }
    }

    public void refreshToken() {
        this.token = UUID.randomUUID();
        this.dateCreateToken = Instant.now();
        Instant instant = Instant.now();
        instant.plus(30, DAYS);
        this.dateDeathToken = instant;
    }

}
