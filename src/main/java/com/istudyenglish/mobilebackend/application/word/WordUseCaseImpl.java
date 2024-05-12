package com.istudyenglish.mobilebackend.application.word;

import com.istudyenglish.mobilebackend.adapter.Word.WordDAO;
import com.istudyenglish.mobilebackend.domain.dictionary.Word;
import com.istudyenglish.mobilebackend.port.in.word.WordUseCase;
import com.istudyenglish.mobilebackend.port.out.word.WordDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WordUseCaseImpl implements WordUseCase {
//todo проверь лишние объекты, конструктор и имплиментацию

    WordDBPort wordDBPort;


    @Autowired
    public WordUseCaseImpl(WordDAO wordDAO) {
        this.wordDBPort=wordDAO;

    }

    @Override
    public List<Word> get(String wordStr) {
        List<String> strings = new ArrayList<>();
        strings.add(wordStr);
        return get(strings);
    }


    @Override
    public List<Word> get(List<String> wordStrList) {
        return wordDBPort.get(wordStrList);
    }



}
