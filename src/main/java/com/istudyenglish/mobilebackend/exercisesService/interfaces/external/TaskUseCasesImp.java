package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.CustomException;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.task.TaskDAO;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.task.TaskDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TaskUseCasesImp implements TaskUseCases{
    TaskDBPort taskDBPort;


    @Autowired
    public TaskUseCasesImp(TaskDAO taskDAO) {
        this.taskDBPort = taskDAO;
    }

    @Override
    public List<Task> getNextOnlyStudy(UUID userUUID, int count){

        return taskDBPort.getNextOnlyStudy(userUUID,count);
    }

    @Override
    public Task getByUserAndExercise(UUID userUUID, UUID exerciseUUID) {
        return taskDBPort.getByUserAndExercise(userUUID,exerciseUUID);
    }

    @Override
    public Task getOnUUID(UUID userUUID, UUID taskUUID) throws CustomException {
        Task task = taskDBPort.genOnUUID(taskUUID);
        checkTaskBelongsUser(userUUID,task);
        return task;
    }

    @Override
    public void giveAnswer(UUID userUUID, UUID taskUUID, UUID answerUUID, Instant timeAnswer) throws CustomException {
        //todo
    }

    @Override
    public void create(List<UUID> exerciseUUIDList,UUID userUUID) {
        //todo проверку что у юзера достаточно баланса на таски
        List<Task> taskList = new ArrayList<>();
        for(UUID exerciseUUID: exerciseUUIDList){
            taskList.add(new Task(exerciseUUID,userUUID));
        }

        taskDBPort.create(taskList);
    }


    private void checkTaskBelongsUser(UUID userUUID, Task task) throws CustomException {
        if(!task.checkUserAffiliation(userUUID)){
            throw new CustomException();
        }
    }

    @Override
    public void updateFromAnswer(UUID userUUID, UUID exerciseUUID, boolean isTrue,Instant instantAnswer) {
        Task task = getByUserAndExercise(userUUID,exerciseUUID);
        if(isTrue){
            task.updateIfAnswerIsTrue(instantAnswer);
        }
        else task.updateIfAnswerIsFalse(instantAnswer);
    }
}
