package com.istudyenglish.mobilebackend.dictionary.interfaces.external;

import com.istudyenglish.mobilebackend.dictionary.domain.Word;

import java.util.UUID;

public interface WordsUseCases {
    public Word getUUID(UUID uuid);

}
