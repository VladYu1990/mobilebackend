package com.istudyenglish.mobilebackend.dictionary.interfaces.internal;

import com.istudyenglish.mobilebackend.dictionary.domain.Source;

import java.util.List;
import java.util.UUID;

public interface SourceDBPort {


    Source getUUID(UUID uuid);
    void create(Source source);
    List<Source> getAll();
}
