package com.istudyenglish.mobilebackend.exercisesService.domain.answer;


import com.istudyenglish.mobilebackend.dictionary.domain.Language;
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
    private Language language;

    public Answer(String value, Language language) {
        this.uuid = UUID.randomUUID();
        this.value = value;
        this.language = language;
    }

}
