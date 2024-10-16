package com.istudyenglish.mobilebackend.dictionary.domain;


import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Word{

    /**
     * id слова
     */
    private UUID uuid;
    /**
     * написание
     */
    private String value;
    /**
     * перевод
     */
    private String translate;
    /**
     * язык написания
     */
    private Language valueLanguage;
    /**
     * язык перевода
     */
    private Language translateLanguage;
    /**
     * код части речи
     */
    private PartOfSpeech partOfSpeech;

    public Word(String value, String translate, Language valueLanguage, Language translateLanguage, PartOfSpeech partOfSpeechCode) {
        this.uuid = UUID.randomUUID();
        this.value = value;
        this.translate = translate;
        this.valueLanguage = valueLanguage;
        this.translateLanguage = translateLanguage;
        this.partOfSpeech = partOfSpeechCode;
    }
}
