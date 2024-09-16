package com.istudyenglish.mobilebackend.tasksService.adapters;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AnswerForView {
    private String answerUUID;
    private String value;
    private Boolean isTrueAnswer;
}
