package com.istudyenglish.mobilebackend.port.out.word;

import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.domain.dictionary.Word;

import java.util.List;
import java.util.UUID;

public interface WordDBPort {


    List<Word> get(List<String> valueOrTranslate);

}
