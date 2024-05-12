package com.istudyenglish.mobilebackend.port.in.word;

import com.istudyenglish.mobilebackend.domain.dictionary.Source;
import com.istudyenglish.mobilebackend.domain.dictionary.Word;

import java.util.List;


public interface WordUseCase {

    List<Word> get(String wordStr);

    List<Word> get(List<String> wordStrList);
}
