package com.istudyenglish.mobilebackend.tasksService.adapters;


import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.tasksService.interfaces.external.ExerciseUseCases;
import com.istudyenglish.mobilebackend.tasksService.interfaces.external.ExerciseUseCasesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskForViewAdapter {
    ExerciseUseCases exerciseUseCases;
    //todo add answerUseCases


    @Autowired
    public TaskForViewAdapter(ExerciseUseCasesImp exerciseUseCasesImp) {
        this.exerciseUseCases = exerciseUseCasesImp;
    }

    public TaskForView getTaskForView(Task task){
        //TODO сначала получаем упражнение, из него берем вопрос(велъю). далее получаем из него ответ, его в адаптер ответов - на выходе получаем список ответов
        exerciseUseCases.getOnUUIDs(task.getExerciseUUID());
        return new TaskForView();
    }


    public List<TaskForView> getTaskForViews(List<Task> taskList){
        List<TaskForView> taskForViewList = new ArrayList<>();
        for(Task task : taskList){
            taskForViewList.add(getTaskForView(task));
        }
        return taskForViewList;
    }


}
