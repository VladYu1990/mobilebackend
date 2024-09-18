package com.istudyenglish.mobilebackend.adapter.task;

import com.istudyenglish.mobilebackend.adapter.answer.ConvertorAnswerDTO;
import com.istudyenglish.mobilebackend.adapter.answer.AnswerDTO;
import com.istudyenglish.mobilebackend.application.answer.AnswerUseCaseImpl;
import com.istudyenglish.mobilebackend.application.similarAnswer.SimilarAnswerUseCaseImpl;
import com.istudyenglish.mobilebackend.application.exercise.ExerciseUseCaseImpl;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.port.in.answer.AnswerUseCase;
import com.istudyenglish.mobilebackend.port.in.similarAnswer.SimilarAnswerUseCase;
import com.istudyenglish.mobilebackend.port.in.exercise.ExerciseUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class ConvertorToTaskDTO {


    ConvertorAnswerDTO convertorAnswerDTO;
    AnswerUseCase answerUseCase;
    ExerciseUseCase exerciseUseCase;
    SimilarAnswerUseCase similarAnswerUseCase;

    @Autowired
    public ConvertorToTaskDTO(ConvertorAnswerDTO convertorAnswerDTO, AnswerUseCaseImpl answerUseCase, ExerciseUseCaseImpl exerciseUseCase, SimilarAnswerUseCaseImpl similarAnswerUseCase) {
        this.convertorAnswerDTO = convertorAnswerDTO;
        this.answerUseCase = answerUseCase;
        this.exerciseUseCase = exerciseUseCase;
        this.similarAnswerUseCase = similarAnswerUseCase;
    }

    public TaskDTO convert(Task task, int count){

        Exercise exercise = exerciseUseCase.get(task.getExerciseUUID());

        return TaskDTO.builder().
                code(task.getUuid().toString()).
                answers(addCollectionAnswerDTO(exercise.getTranslate(),count)).
                question(exercise.getValue()).
                build();
    }

    public Collection<TaskDTO> convert(Collection<Task> taskCollection, int count){
        Collection<TaskDTO> taskDTOCollection = new ArrayList<TaskDTO>();

        for (Task i:taskCollection){
            taskDTOCollection.add(convert(i,count));
        }
        return taskDTOCollection;
    }

    private Collection<AnswerDTO> addCollectionAnswerDTO(String value, int count){

        return convertorAnswerDTO.convert(answerUseCase.getByString(value),count);
    }
}
