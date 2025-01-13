package com.istudyenglish.mobilebackend.exercisesService.adapters;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AnswerForView {
    private String uuid;
    private String value;
    private String language;
    private boolean isTrue;

    public AnswerForView(String uuid, String value, String language, boolean isTrue) {
        this.uuid = uuid;
        this.value = value;
        this.language = language;
        this.isTrue = isTrue;
    }

    public AnswerForView(int i) {
        this.uuid = "";
        this.value = def(i);
        this.language = "rus";
        this.isTrue = true;
    }

    private String def(int i){
        String s = "";
        switch (i){
            case (1):{s = "жаль"; break;}
            case (2):{s = "печаль"; break;}
            case (3):{s = "тоска"; break;}
            case (4):{s = "ну и ладно"; break;}
            default:{def(i%4); break;}
        }
        return s;
    }

}
