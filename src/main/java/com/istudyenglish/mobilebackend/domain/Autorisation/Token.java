package com.istudyenglish.mobilebackend.domain.Autorisation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

@AllArgsConstructor
@Getter
@Builder
public class Token {

    /**
     * Токен пользователя
     */
    private UUID token;
    /**
     * Дата/время создания токена
     */
    private Instant dateCreate;
    /**
     * Дата/время смерти токена
     */
    private Instant dateDeath;
    /**
     * User UUID
     */
    private UUID userUUID;

    public Token(UUID userUUID) {
        this.token = UUID.randomUUID();
        this.dateCreate = Instant.now();
        Instant instant = Instant.now();
        instant.plus(30, DAYS);
        this.dateDeath = instant;

        this.userUUID = userUUID;

    }

    public boolean checkTokenIsALife() {
        if (this.dateCreate.isAfter(Instant.now())) {
            return true;
        } else {
            return false;
        }
    }
}
