package com.istudyenglish.mobilebackend.dictionary.interfaces.external;

import com.istudyenglish.mobilebackend.dictionary.domain.Word;
import com.istudyenglish.mobilebackend.dictionary.interfaces.internal.WordDAO;
import com.istudyenglish.mobilebackend.dictionary.interfaces.internal.WordDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WordUseCasesImp implements WordsUseCases {

    private WordDBPort wordDBPort;

    @Autowired
    public WordUseCasesImp(WordDAO wordDAO) {
        this.wordDBPort = wordDAO;
    }

    @Override
    public Word getUUID(UUID uuid) {
        return wordDBPort.getUUID(uuid);
    }
}
