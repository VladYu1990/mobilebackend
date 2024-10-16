package com.istudyenglish.mobilebackend.exercisesService.adapters;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class AnswerForView {
    private String uuid;
    private String value;
    private String language;
    private boolean isTrue;
}
