package com.istudyenglish.mobilebackend.exercisesService.adapters;


import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExerciseForViewBuilder {
    private AnswerForViewBuilder answerForViewBuilder;

    @Autowired
    public ExerciseForViewBuilder(AnswerForViewBuilder answerForViewBuilder) {
        this.answerForViewBuilder = answerForViewBuilder;
    }

    public List<ExerciseForView> build(List<Exercise> exerciseList, int maxCountAnswer){
        List<ExerciseForView> exerciseForViewList = new ArrayList<>();
        for(Exercise e:exerciseList){
            exerciseForViewList.add(build(e,maxCountAnswer));
        }
        if((exerciseForViewList).isEmpty()){
            exerciseForViewList.add(new ExerciseForView(answerForViewBuilder.buildForEmpty(maxCountAnswer)));
        }
        return exerciseForViewList;
    }

    private ExerciseForView build(Exercise exercise,int maxCountAnswer){

        return ExerciseForView.builder().
                exerciseUUID(exercise.getUuid().toString()).
                question(exercise.getQuestion().getValue()).
                //todo добавить примеры в БД & упражнение и подтянуть
                example("когда-то тут будет хороший пример, пока так").
                typesOfExercise(exercise.getTypesOfExercise()).
                answerOptions(answerForViewBuilder.build(exercise.getAnswer(),maxCountAnswer)).
                build();
    }

}
