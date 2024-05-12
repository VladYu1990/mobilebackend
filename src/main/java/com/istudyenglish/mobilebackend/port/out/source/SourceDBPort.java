package com.istudyenglish.mobilebackend.port.out.source;

import com.istudyenglish.mobilebackend.domain.dictionary.Source;

import java.util.List;

public interface SourceDBPort {

    List<Source> getWord(String string);
}
