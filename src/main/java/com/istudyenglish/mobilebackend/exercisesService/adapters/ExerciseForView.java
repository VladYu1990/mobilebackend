package com.istudyenglish.mobilebackend.exercisesService.adapters;



import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.TypesOfExercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ExerciseForView {
    private String exerciseUUID;
    private String question;
    private String example;
    private TypesOfExercise typesOfExercise;
    private List<AnswerForView> answerOptions;


    public ExerciseForView(List<AnswerForView> answerForViewList) {
        this.exerciseUUID = null;
        this.question = "На сегодня больше заданий нет";
        this.typesOfExercise = TypesOfExercise.reading;
        this.answerOptions = answerForViewList;
    }
}
