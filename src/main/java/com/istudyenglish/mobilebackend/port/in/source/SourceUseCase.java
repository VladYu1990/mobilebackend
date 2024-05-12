package com.istudyenglish.mobilebackend.port.in.source;

import com.istudyenglish.mobilebackend.domain.dictionary.Source;

import java.util.List;


public interface SourceUseCase {

    List<Source> get(String sourceStr);
}
