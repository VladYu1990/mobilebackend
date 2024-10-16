package com.istudyenglish.mobilebackend.exercisesService.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class Question {
    private UUID uuid;
    private String value;
    private String language;
}
