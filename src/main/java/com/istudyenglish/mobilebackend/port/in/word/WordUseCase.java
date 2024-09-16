package com.istudyenglish.mobilebackend.port.in.word;

import com.istudyenglish.mobilebackend.dictionary.domain.Word;

import java.util.List;


public interface WordUseCase {

    List<Word> get(String wordStr);

    List<Word> get(List<String> wordStrList);
}
