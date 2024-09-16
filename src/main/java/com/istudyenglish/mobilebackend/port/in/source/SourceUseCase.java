package com.istudyenglish.mobilebackend.port.in.source;

import java.util.List;


public interface SourceUseCase {

    List<Source> get(String sourceStr);
}
