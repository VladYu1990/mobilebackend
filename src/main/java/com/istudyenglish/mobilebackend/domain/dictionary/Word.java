package com.istudyenglish.mobilebackend.domain.dictionary;

import com.istudyenglish.mobilebackend.domain.Education.PartsOfSpeech;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Word extends Source{
    
    private PartsOfSpeech partsOfSpeech;

    private TypesOfSource typesOfSource;


}
