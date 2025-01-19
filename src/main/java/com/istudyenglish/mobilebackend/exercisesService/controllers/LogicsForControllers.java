package com.istudyenglish.mobilebackend.exercisesService.controllers;


import com.istudyenglish.mobilebackend.dictionary.domain.Source;
import com.istudyenglish.mobilebackend.dictionary.interfaces.external.SourceUseCases;
import com.istudyenglish.mobilebackend.dictionary.interfaces.external.SourceUseCasesImp;
import com.istudyenglish.mobilebackend.exercisesService.adapters.ExerciseForView;
import com.istudyenglish.mobilebackend.exercisesService.adapters.ExerciseForViewBuilder;
import com.istudyenglish.mobilebackend.exercisesService.domain.task.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.exercise.Exercise;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.external.*;
import com.istudyenglish.mobilebackend.userService.domain.User;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCases;
import com.istudyenglish.mobilebackend.userService.interfaces.external.UserUseCasesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class LogicsForControllers {
    private TaskUseCases taskUseCases;
    private AnswerUseCases answerUseCases;
    private ExerciseForViewBuilder exerciseForViewBuilder;
    private SimilarAnswerUseCases similarAnswerUseCases;
    private UserUseCases userUseCases;
    private ExerciseUseCases exerciseUseCases;
    private SourceUseCases sourceUseCases;

    @Autowired
    public LogicsForControllers(TaskUseCasesImp taskUseCases, AnswerUseCasesImp answerUseCases, ExerciseForViewBuilder exerciseForViewBuilder, SimilarAnswerUseCasesImp similarAnswerUseCases, UserUseCasesImp userUseCases, ExerciseUseCasesImp exerciseUseCases, SourceUseCasesImp sourceUseCases) {
        this.taskUseCases = taskUseCases;
        this.answerUseCases = answerUseCases;
        this.exerciseForViewBuilder = exerciseForViewBuilder;
        this.similarAnswerUseCases = similarAnswerUseCases;
        this.userUseCases = userUseCases;
        this.exerciseUseCases = exerciseUseCases;
        this.sourceUseCases = sourceUseCases;
    }

    public List<ExerciseForView> nextExercises(UUID user, int maxCountTasks,int maxCountAnswers) {
        User userOb= userUseCases.getUUID(user);
        List<Task> taskList =taskUseCases.getNextOnlyStudy(userOb,maxCountTasks);
        List<Exercise> exerciseList = new ArrayList<>();
        for(Task t:taskList){
            exerciseList.add(t.getExercise());
        }

        return exerciseForViewBuilder.build(exerciseList,maxCountAnswers);
    }

    public void putSimilarAnswer(String codeAnswer) {
        Answer answer = answerUseCases.getByUUID(UUID.fromString(codeAnswer));
        similarAnswerUseCases.update(answer);
    }

    public void putSimilarAnswer(){

        similarAnswerUseCases.updateAll();
    }

    public void createExercises() {
        List<Source> sourceList = sourceUseCases.getAllSources();
        for(Source s:sourceList){
            exerciseUseCases.create(s);
        }
    }

    public void createExercise(String sourceStringUUID){
        UUID sourceUUID = UUID.fromString(sourceStringUUID);
        Source source = sourceUseCases.getUUID(sourceUUID);
    }

    public void addTaskAll(UUID userUUID) {
        List<Exercise> exerciseList = exerciseUseCases.getAll();
        User user = userUseCases.getUUID(userUUID);
        taskUseCases.create(exerciseList,user);
    }


    public void AddTask(UUID exerciseUUID,UUID userUUID) {
        Exercise exercise = exerciseUseCases.getOnUUID(exerciseUUID);
        User user =userUseCases.getUUID(userUUID);
        taskUseCases.create(exercise,user);
    }



}
