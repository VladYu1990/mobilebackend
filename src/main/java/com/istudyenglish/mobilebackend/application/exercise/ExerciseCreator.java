package com.istudyenglish.mobilebackend.application.exercise;

import com.istudyenglish.mobilebackend.domain.Education.Exercise;
import com.istudyenglish.mobilebackend.domain.Education.Languages;
import com.istudyenglish.mobilebackend.domain.Education.TypesOfDirectionsTranslations;
import com.istudyenglish.mobilebackend.domain.Education.TypesOfExercise;
import com.istudyenglish.mobilebackend.domain.dictionary.Source;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Component
public class ExerciseCreator {



    public Collection<Exercise> create(Source source) {

        Collection<Exercise> exercises = new ArrayList<Exercise>();
//todo придумать как переписать проще и понятнее
        for (Languages language1 : Languages.getList()) {
            if (source.getMapValue().containsKey(language1)) {
                for (Languages language2 : Languages.getList()) {
                    if (source.getMapValue().containsKey(language2) ||
                            !language1.equals(language2)) {
                        createForLang1Lang2(source, language1, language2);
                    }
                }
            }
        }
        return  exercises;
    }

    private Collection<Exercise> createForLang1Lang2(Source source,Languages language1,Languages language2) {
        Collection<Exercise> exercises= new ArrayList<Exercise>();
        for(TypesOfExercise typesOfExercise:TypesOfExercise.getList()){
            exercises.add(Exercise.builder().
                    uuid(UUID.randomUUID()).
                    typesOfExercise(typesOfExercise).
                    sourceUUID(source.getUuid()).
                    value(source.getMapValue().get(language1)).
                    translate(source.getMapValue().get(language2)).
                    typeOfDirectionsTranslations(TypesOfDirectionsTranslations.valueOf(language1,language2)).
                    build());
        }
        return exercises;
    }

}
