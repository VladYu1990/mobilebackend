package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.adapter.task.ConvertorToTaskDTO;
import com.istudyenglish.mobilebackend.adapter.task.TaskDAO;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.CustomException.CustomExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.istudyenglish.mobilebackend.domain.Education.Exercise;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.port.in.task.TaskUseCase;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Component
public class TaskUseCaseImpl implements TaskUseCase {

    private ConvertorToTaskDTO convertorToTaskDTO;

    private TaskCreator taskCreator;

    private TaskDAO taskDAO;

    private TaskAnswerChecker taskAnswerChecker;

    @Autowired
    public TaskUseCaseImpl(ConvertorToTaskDTO convertorToTaskDTO, TaskCreator taskCreator, TaskDAO taskDAO, TaskAnswerChecker taskAnswerChecker) {
        this.convertorToTaskDTO = convertorToTaskDTO;
        this.taskCreator = taskCreator;
        this.taskDAO = taskDAO;
        this.taskAnswerChecker = taskAnswerChecker;
    }

     public void createTask(Student student, Exercise exercise,Instant instant){
        Collection<Exercise> exerciseList = new ArrayList<Exercise>();
        exerciseList.add(exercise);
        createTasks(student,exerciseList,instant);
    }

    public void createTasks(Student student, Collection<Exercise> exerciseList,Instant instant) {
        Collection<Task> taskCollection = new ArrayList<Task>();
        for(Exercise i:exerciseList){
            taskCollection.add(
                    taskCreator.create(
                            student.getUuid(),i.getUuid()));
        }
        taskDAO.save(taskCollection,instant);
    }
    public Task get(UUID taskUUID){
        return taskDAO.get(taskUUID);
    }

    public Collection<Task> getNextTasks(Student student, int amount,Instant time) {
        //todo
        Collection<Task> taskCollection = new ArrayList<Task>();
        ArrayList<Task> taskCollectionTemp = (ArrayList<Task>) taskDAO.getNext(student,time);

        for(int i = 0; i<amount; i++){
            taskCollection.add(taskCollectionTemp.get(i));
        }

        return taskCollection;
    }


    public void checkStudentAffiliation(Task task, UUID studentUUID) throws CustomException {
        if(!task.checkStudentAffiliation(studentUUID)){
            throw new CustomException(CustomExceptionCode.AssignmentDoesNotBelongToStudent);
        }
    }

    public void updateAfterReply(Task task, boolean trueReply,Instant time) {
        if(trueReply){
            task.updateIfAnswerIsTrue(time);
        }
        else {
            task.updateIfAnswerIsFalse(time);
        }

        taskDAO.update(task);
    }
}
