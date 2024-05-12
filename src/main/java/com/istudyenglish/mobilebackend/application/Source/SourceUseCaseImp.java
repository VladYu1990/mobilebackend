package com.istudyenglish.mobilebackend.application.Source;

import com.istudyenglish.mobilebackend.domain.dictionary.Source;
import com.istudyenglish.mobilebackend.port.in.source.SourceUseCase;
import com.istudyenglish.mobilebackend.port.out.source.SourceDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SourceUseCaseImp implements SourceUseCase {

    SourceDBPort sourceDBPort;

    @Autowired
    public SourceUseCaseImp() {
    }

    @Override
    public List<Source> get(String sourceStr) {
        return null;
    }
}
