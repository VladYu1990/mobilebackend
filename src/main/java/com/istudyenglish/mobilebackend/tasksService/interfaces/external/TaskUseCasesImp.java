package com.istudyenglish.mobilebackend.tasksService.interfaces.external;

import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.CustomException.CustomExceptionCode;
import com.istudyenglish.mobilebackend.tasksService.domain.Task;
import com.istudyenglish.mobilebackend.tasksService.interfaces.internal.task.TaskDAO;
import com.istudyenglish.mobilebackend.tasksService.interfaces.internal.task.TaskDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
public class TaskUseCasesImp implements TaskUseCases{
    TaskDBPort taskDBPort;
    ExerciseUseCases exerciseUseCases;


    @Autowired
    public TaskUseCasesImp(TaskDAO taskDAO, ExerciseUseCasesImp exerciseUseCasesImp) {
        this.taskDBPort = taskDAO;
        this.exerciseUseCases = exerciseUseCasesImp;
    }

    @Override
    public List<Task> getNext(UUID userUUID, int count){

        return taskDBPort.getNext(userUUID,count);
    }

    @Override
    public Task getOnUUID(UUID userUUID, UUID taskUUID) throws CustomException {
        Task task = taskDBPort.genOnUUID(taskUUID);
        checkTaskBelongsUser(userUUID,task);
        return task;
    }

    @Override
    public void giveAnswer(UUID userUUID, UUID taskUUID, UUID answerUUID, Instant timeAnswer) throws CustomException {
        Task task = taskDBPort.genOnUUID(taskUUID);
        checkTaskBelongsUser(userUUID,task);
        if(exerciseUseCases.checkAnswer(task.getExerciseUUID(),answerUUID)){
            task.updateIfAnswerIsTrue(timeAnswer);
        }
        else {
            task.updateIfAnswerIsFalse(timeAnswer);
        }
    }

    @Override
    public void create(UUID userUUID, UUID exerciseUUID) {
        Task task = new Task(exerciseUUID,userUUID);
        taskDBPort.create(task);
    }

    @Override
    public void checkTaskBelongsUser(UUID userUUID, Task task) throws CustomException {
        if(!task.checkUserAffiliation(userUUID)){
            throw new CustomException(CustomExceptionCode.AssignmentDoesNotBelongToStudent,"у юзера не такой таски");
        }
    }
}
