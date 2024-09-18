package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;


import com.istudyenglish.mobilebackend.exercisesService.adapters.ExerciseForView;
import com.istudyenglish.mobilebackend.exercisesService.adapters.ExerciseForViewAdapter;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ExerciseForViewUseCasesImp implements ExerciseForViewUseCases{
    TaskUseCases taskUseCases;
    ExerciseUseCases exerciseUseCases;
    ExerciseForViewAdapter exerciseForViewAdapter;
    AnswerUseCases answerUseCases;


    @Override
    public List<ExerciseForView> getNextList(UUID userUUID, int count) {

        List<ExerciseForView> exerciseForViewList = new ArrayList<>();

        List<Task> taskList = taskUseCases.getNextOnlyStudy(userUUID,count);

        List<UUID> exerciseUUIDList = new ArrayList<>();
        for(Task task:taskList){
            exerciseUUIDList.add(task.getExerciseUUID());
        }

        List<Exercise> exerciseList = exerciseUseCases.getOnUUIDs(exerciseUUIDList);

        for(Exercise exercise:exerciseList){
            exerciseForViewList.add(adapt(exercise));
        }

        return exerciseForViewList;
    }

    private ExerciseForView adapt(Exercise exercise){

         return exerciseForViewAdapter.adapt(exercise,getAnswerOptions(exercise));
    }

    private List<Answer> getAnswerOptions(Exercise exercise){
        return answerUseCases.getAnswerWithSimilarByUUID(exercise.getTrueAnswerCode());
    }
}
