package com.istudyenglish.mobilebackend.dictionary.domain;

import com.istudyenglish.mobilebackend.domain.Education.Languages;
import com.istudyenglish.mobilebackend.domain.Education.PartsOfSpeech;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
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
    private Languages valueLanguage;
    /**
     * язык перевода
     */
    private Languages translateLanguage;
    /**
     * код части речи
     */
    private PartsOfSpeech partOfSpeech;

    public Word(String value, String translate, Languages valueLanguage, Languages translateLanguage, PartsOfSpeech partOfSpeechCode) {
        this.uuid = UUID.randomUUID();
        this.value = value;
        this.translate = translate;
        this.valueLanguage = valueLanguage;
        this.translateLanguage = translateLanguage;
        this.partOfSpeech = partOfSpeechCode;
    }
}
