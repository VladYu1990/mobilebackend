package com.istudyenglish.mobilebackend.domain.dictionary;

import com.istudyenglish.mobilebackend.domain.Education.Languages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public abstract class Source {
    /**
     * Тип источника
     */
    private TypesOfSource typesOfSource;
    /**
     * Код источника
     */
    private String code;
    /**
     * UUID источника
     */
    private UUID uuid;
    /**
     * Коллекция значений на разных языках
     */
    private Map<Languages,String> mapValue;
    /**
     * Дата/время обновления
     */
    private Instant instant;
    /**
     * Проверен
     */
    private boolean correct;

}
