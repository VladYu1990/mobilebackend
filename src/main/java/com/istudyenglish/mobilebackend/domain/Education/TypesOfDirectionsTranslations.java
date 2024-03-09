package com.istudyenglish.mobilebackend.domain.Education;

/**
 * Тип упражнения
 */
public enum TypesOfDirectionsTranslations {
    /**
     * рус -> англ
     */
    RUS_ENG,
    /**
     * англ -> рус
     */
    ENG_RUS;

    public static TypesOfDirectionsTranslations valueOf(Languages languages1,Languages languages2){
        String value = languages1.toString() + "" + languages2.toString();
        return TypesOfDirectionsTranslations.valueOf(value);
    }
}
