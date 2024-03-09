package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.adapter.task.CreatorTaskDTO;
import com.istudyenglish.mobilebackend.adapter.task.TaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.istudyenglish.mobilebackend.domain.Education.Answer;
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

    private CreatorTaskDTO creatorTaskDTO;

    private TaskCreator taskCreator;

    private TaskDAO taskDAO;

    private TaskAnswerChecker taskAnswerChecker;

    @Autowired
    public TaskUseCaseImpl(CreatorTaskDTO creatorTaskDTO, TaskCreator taskCreator, TaskDAO taskDAO, TaskAnswerChecker taskAnswerChecker) {
        this.creatorTaskDTO = creatorTaskDTO;
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
    public Task getByUUID(UUID taskUUID){
        return taskDAO.getByUUID(taskUUID);
    }

    @Override
    public Task getByCode(String taskCode) {
        return taskDAO.getByCode(taskCode);
    }

    public Task getNextTask(Student student,int amount) {
        //todo
        return null;
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
    public boolean checkAnswer(Task task, Answer answer) {
        task.getExerciseUUID();
        //todo
        //Exercise.getAnswer()==Answer.getByUUID(uuidAnswer).getValue;
        return false;
    }

     public void delete(Task task, Instant instant){
        Collection<Task> taskCollection = new ArrayList<Task>();
        taskCollection.add(task);
        deleteTasks(taskCollection,instant);
    }

    public void deleteTasks(Collection<Task> taskCollection, Instant instant){
        taskDAO.delete(taskCollection,instant);
    }


    public void processAnswer(UUID taskUUID, UUID answerUUID,Instant instant) {
        Task task = taskDAO.getByUUID(taskUUID);
        if(taskAnswerChecker.check(task,answerUUID)){
            task.updateIfAnswerIsTrue(instant);
        }
        else {
            task.updateIfAnswerIsFalse(instant);
        }
        Collection<Task> taskCollection = new ArrayList<Task>();
        taskCollection.add(task);
        taskDAO.update(taskCollection,instant);
    }

    public boolean checkStudentAffiliation(Task task, UUID studentUUID) {
        return task.checkStudentAffiliation(studentUUID);
    }


}
