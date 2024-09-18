package com.istudyenglish.mobilebackend.exercisesService.adapters;

import com.istudyenglish.mobilebackend.domain.Education.TypesOfExercise;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.List;

@AllArgsConstructor
@Getter
public class ExerciseForView {
    private String exerciseUUID;
    private String question;
    private TypesOfExercise typesOfExercise;
    private List<Answer> answerOptions;

}
