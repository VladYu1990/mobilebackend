package com.istudyenglish.mobilebackend.port.out.word;

import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.domain.dictionary.Word;

import java.util.UUID;

public interface WordDBPort {

    void create(Word word);

    User get(UUID uuid) throws Exception;

    User get(String code) throws Exception;

    void update(Word word);
}
