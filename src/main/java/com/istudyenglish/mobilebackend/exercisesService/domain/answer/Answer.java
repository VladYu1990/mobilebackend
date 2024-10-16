package com.istudyenglish.mobilebackend.exercisesService.domain.answer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class Answer {
    private UUID uuid;
    private String value;
    private String language;
}
