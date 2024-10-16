package com.istudyenglish.mobilebackend.exercisesService.adapters;


import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.TypesOfExercise;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.List;

@AllArgsConstructor
@Getter
public class ExerciseForView {
    private String exerciseUUID;
    private String question;
    private TypesOfExercise typesOfExercise;
    private List<AnswerForView> answerOptions;

}
