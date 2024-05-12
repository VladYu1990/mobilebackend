package com.istudyenglish.mobilebackend.domain.dictionary;

import com.istudyenglish.mobilebackend.domain.Education.PartsOfSpeech;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Word{

    /**
     * id слова
     */
    private int id;
    /**
     * написание на английском языке
     */
    String eng;
    /**
     * написание на русском языке
     */
    String rus;
    /**
     * часть речи
     */
    private PartsOfSpeech partsOfSpeech;



}
