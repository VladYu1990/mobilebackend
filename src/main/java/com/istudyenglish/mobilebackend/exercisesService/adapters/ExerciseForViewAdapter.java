package com.istudyenglish.mobilebackend.exercisesService.adapters;


import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExerciseForViewAdapter {
    AnswerForViewAdapter answerForViewAdapter;



    @Autowired
    public ExerciseForViewAdapter(AnswerForViewAdapter answerForViewAdapter) {
        this.answerForViewAdapter = answerForViewAdapter;
    }

    public ExerciseForView adapt(Exercise exercise,List<Answer> answerList){

        return new ExerciseForView(
                exercise.getUuid().toString(),
                exercise.getQuestion().getValue(),
                exercise.getTypesOfExercise(),
                answerForViewAdapter.adapt(answerList,exercise.getAnswer().getUuid()));
    }
}
