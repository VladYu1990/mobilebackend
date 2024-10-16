package com.istudyenglish.mobilebackend.userService.adapters.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class Token {
    private UUID uuid;
    private Instant dateOfDeathToken;
}
