package com.istudyenglish.mobilebackend.dictionary.interfaces.external;

import com.istudyenglish.mobilebackend.dictionary.domain.Language;
import com.istudyenglish.mobilebackend.dictionary.domain.PartOfSpeech;
import com.istudyenglish.mobilebackend.dictionary.domain.Source;
import com.istudyenglish.mobilebackend.dictionary.interfaces.internal.SourceDAO;
import com.istudyenglish.mobilebackend.dictionary.interfaces.internal.SourceDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class SourceUseCasesImp implements SourceUseCases {

    private SourceDBPort sourceDBPort;

    @Autowired
    public SourceUseCasesImp(SourceDAO wordDAO) {
        this.sourceDBPort = wordDAO;
    }

    @Override
    public Source getUUID(UUID uuid) {
        return sourceDBPort.getUUID(uuid);
    }

    @Override
    public void create(Map<String, String> wordMap) {
        Source source = Source.builder().
                uuid(UUID.randomUUID()).
                textFrom(wordMap.get("value")).
                fromLanguage(Language.valueOf(wordMap.get("value_language"))).
                exampleForFrom(wordMap.get("example")).
                textTo(wordMap.get("translate")).
                toLanguage(Language.valueOf(wordMap.get("translate_language"))).
                exampleForTo(wordMap.get("example_for_translate_language")).
                partOfSpeech(PartOfSpeech.valueOf(wordMap.get("part_of_speech"))).
                build();

        save(source);
    }

    private void save(Source source) {
        try{
            sourceDBPort.create(source);
        }
        catch (DataAccessException ignored){
        }
    }

    @Override
    public List<Source> getAllSources() {
        return sourceDBPort.getAll();
    }
}
