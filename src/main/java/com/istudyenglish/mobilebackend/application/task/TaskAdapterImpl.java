package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.adapter.task.ConvertorToTaskDTO;
import com.istudyenglish.mobilebackend.adapter.task.TaskDTO;
import com.istudyenglish.mobilebackend.application.answer.AnswerChecker;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.answer.AnswerUseCaseImpl;
import com.istudyenglish.mobilebackend.application.exercise.ExerciseUseCaseImpl;
import com.istudyenglish.mobilebackend.application.student.StudentUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Autorisation.Token;
import com.istudyenglish.mobilebackend.domain.Education.Exercise;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.port.in.exercise.ExerciseUseCase;
import com.istudyenglish.mobilebackend.port.in.student.StudentUseCase;
import com.istudyenglish.mobilebackend.port.in.task.TaskAdapter;
import com.istudyenglish.mobilebackend.port.in.task.TaskUseCase;
import com.istudyenglish.mobilebackend.port.in.token.TokenUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class TaskAdapterImpl implements TaskAdapter {
//todo проверь лишние объекты, конструктор и имплиментацию
    StudentUseCase studentUseCase;
    TaskUseCase taskUseCase;
    ExerciseUseCase exerciseUseCase;
    ConvertorToTaskDTO convertorToTaskDTO;

    @Autowired
    public TaskAdapterImpl(StudentUseCase studentUseCase, TaskUseCase taskUseCase, ExerciseUseCase exerciseUseCase, ConvertorToTaskDTO convertorToTaskDTO) {
        this.studentUseCase = studentUseCase;
        this.taskUseCase = taskUseCase;
        this.exerciseUseCase = exerciseUseCase;
        this.convertorToTaskDTO = convertorToTaskDTO;
    }

    public void create(String studentStrUUID, String exerciseStrUUID, Instant time) throws CustomException {
        Student student;
        student = studentUseCase.get(UUID.fromString(studentStrUUID));
        Exercise exercise;
        exercise = exerciseUseCase.get(UUID.fromString(exerciseStrUUID));
        taskUseCase.createTask(student,exercise,time);
    }

    public void create(String studentCode, List<String> strings, Instant time) throws CustomException {
        Token token = tokenUseCase.
        Student student = studentUseCase.get(UUID.fromString(studentCode));
        Collection<Exercise> exercises= exerciseUseCase.get(strings);
        taskUseCase.createTasks(student,exercises,time);
    }

    public Collection<TaskDTO> getNextTask(String studentStrUUID, int amountTasks, int amountAnswer, Instant time) throws CustomException{

        Student student;
        student = studentUseCase.get(UUID.fromString(studentStrUUID));
        Collection<Task> tasks;
        tasks = taskUseCase.getNextTasks(student,amountTasks,time);
        return convertorToTaskDTO.convert(tasks,amountAnswer);


    }




}
