package com.istudyenglish.mobilebackend.dictionary.interfaces.external;

import com.istudyenglish.mobilebackend.dictionary.domain.Source;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SourceUseCases {
    public Source getUUID(UUID uuid);
    public void create(Map<String,String> word);
    public List<Source> getAllSources();

}
