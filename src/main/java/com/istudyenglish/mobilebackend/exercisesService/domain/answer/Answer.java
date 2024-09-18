package com.istudyenglish.mobilebackend.exercisesService.domain.answer;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Answer {
    private UUID uuid;
    private String value;
}
