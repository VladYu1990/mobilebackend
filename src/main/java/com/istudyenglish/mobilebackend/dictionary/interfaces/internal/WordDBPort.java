package com.istudyenglish.mobilebackend.dictionary.interfaces.internal;

import com.istudyenglish.mobilebackend.dictionary.domain.Word;

import java.util.List;
import java.util.UUID;

public interface WordDBPort {


    Word getUUID(UUID uuid);

}
