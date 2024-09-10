package com.istudyenglish.mobilebackend.port.out.source;

import java.util.List;

public interface SourceDBPort {

    List<Source> getWord(String string);
}
