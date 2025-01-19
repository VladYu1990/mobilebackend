package com.istudyenglish.mobilebackend.dictionary.domain;


import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Source {

    /**
     * id слова
     */
    private UUID uuid;
    /**
     * написание
     */
    private String textFrom;
    /**
     * перевод
     */
    private Language fromLanguage;
    /**
     * пример использования на языке написания
     */
    private String exampleForFrom;
    /**
     * язык перевода
     */
    private String textTo;
    /**
     * язык написания
     */
    private Language toLanguage;
    /**
     * пример использования на языке перевода
     */
    private String exampleForTo;
    /**
     * код части речи
     */
    private PartOfSpeech partOfSpeech;

}
