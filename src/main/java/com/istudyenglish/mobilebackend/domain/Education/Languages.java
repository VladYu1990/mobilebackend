package com.istudyenglish.mobilebackend.domain.Education;

import java.util.ArrayList;
import java.util.Collection;

public enum Languages {
    /**
     * Русский
     */
    RUS,
    /**
     * Английский
     */
    ENG;

    public static Collection<Languages> getList(){
        Collection<Languages> languages = new ArrayList<Languages>();
        languages.add(Languages.ENG);
        languages.add(Languages.ENG);

        return languages;
    }

}
