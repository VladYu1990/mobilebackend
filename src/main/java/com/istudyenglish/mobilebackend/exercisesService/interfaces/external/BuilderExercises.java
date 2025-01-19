package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;


import com.istudyenglish.mobilebackend.dictionary.domain.Language;
import com.istudyenglish.mobilebackend.dictionary.domain.Source;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.TypesOfExercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BuilderExercises {
    private AnswerUseCases answerUseCases;

    @Autowired
    public BuilderExercises(AnswerUseCasesImp answerUseCases) {
        this.answerUseCases = answerUseCases;
    }

    public List<Exercise> build(Source source){
        List<Exercise> exerciseList = new ArrayList<>();

        buildReading(exerciseList,source);
        buildListening(exerciseList,source);
        buildWriting(exerciseList,source);
        buildSpeaking(exerciseList,source);

        return  exerciseList;
    }

    private void buildReading(List<Exercise> exerciseList, Source source){

        Answer answerFrom = getAnswerForNexExercise(source.getTextFrom(), source.getFromLanguage());
        Answer answerTo = getAnswerForNexExercise(source.getTextTo(), source.getToLanguage());

        Exercise exercise1 = Exercise.builder().
                uuid(UUID.randomUUID()).
                typesOfExercise(TypesOfExercise.reading).
                sourceUUID(source.getUuid()).
                question(answerFrom).
                answer(answerTo).
                build();

        exerciseList.add(exercise1);
    }

    //todo
    private void buildListening(List<Exercise> exerciseList, Source source){}
    private void buildWriting(List<Exercise> exerciseList, Source source){}
    private void buildSpeaking(List<Exercise> exerciseList, Source source){}


    private Answer getAnswerForNexExercise(String answerValue, Language lang){
        Answer answer = answerUseCases.getByValue(answerValue,lang);
        if(answer == null){
            answerUseCases.create(answerValue,lang);
        }

        while(answer == null){
            answer = answerUseCases.getByValue(answerValue,lang);

        }

        return answer;
    }
}
