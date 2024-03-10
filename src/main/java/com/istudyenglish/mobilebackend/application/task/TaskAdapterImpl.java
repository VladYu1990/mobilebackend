package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.adapter.Validators.StudentUserAffiliationValidator;
import com.istudyenglish.mobilebackend.adapter.Validators.TokenValidator;
import com.istudyenglish.mobilebackend.application.answer.AnswerUseCaseImpl;
import com.istudyenglish.mobilebackend.application.exercise.ExerciseUseCaseImpl;
import com.istudyenglish.mobilebackend.application.student.StudentUseCaseImpl;
import com.istudyenglish.mobilebackend.application.user.UserUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Autorisation.User;
import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.Exercise;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.port.in.answer.AnswerUseCase;
import com.istudyenglish.mobilebackend.port.in.exercise.ExerciseUseCase;
import com.istudyenglish.mobilebackend.port.in.student.StudentUseCase;
import com.istudyenglish.mobilebackend.port.in.task.TaskAdapter;
import com.istudyenglish.mobilebackend.port.in.task.TaskUseCase;
import com.istudyenglish.mobilebackend.port.in.user.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

@Component
public class TaskAdapterImpl implements TaskAdapter {

    StudentUseCase studentUseCase;
    UserUseCase userUseCase;
    TokenValidator tokenValidator;
    StudentUserAffiliationValidator studentUserAffiliationValidator;
    TaskUseCase taskUseCase;
    ExerciseUseCase exerciseUseCase;
    AnswerUseCase answerUseCase;

    @Autowired
    public TaskAdapterImpl(StudentUseCaseImpl studentUseCase, UserUseCaseImpl userUseCase, TokenValidator tokenValidator, StudentUserAffiliationValidator studentUserAffiliationValidator, TaskUseCaseImpl taskUseCase, ExerciseUseCaseImpl exerciseUseCase, AnswerUseCaseImpl answerUseCase) {
        this.studentUseCase = studentUseCase;
        this.userUseCase = userUseCase;
        this.tokenValidator = tokenValidator;
        this.studentUserAffiliationValidator = studentUserAffiliationValidator;
        this.taskUseCase = taskUseCase;
        this.exerciseUseCase = exerciseUseCase;
        this.answerUseCase = answerUseCase;
    }

    public void create(String studentCode, String exerciseUUID, Instant time) throws Exception {
//todo

    }


    public Collection<Task> getNextTask(String studentCode, int amount, Instant time) throws Exception {

        Student student = studentUseCase.get(studentCode);

        return taskUseCase.getNextTasks(student,amount,time);
    }


    public boolean checkAnswer(String token, String studentCode, String taskCode, String answerCode) throws Exception {
        tokenValidator.check(token);
        User user = userUseCase.getByToken(token);
        Student student = studentUseCase.get(studentCode);
        studentUserAffiliationValidator.check(user,student);
        Answer answer = answerUseCase.getByCode(answerCode);
        Task task = taskUseCase.getByCode(taskCode);

        return taskUseCase.checkAnswer(task,answer);
    }


    public void returnAnswer(String token, String studentCode, String taskCode, String answerCode, Instant time) throws Exception {
        tokenValidator.check(token);
        User user = userUseCase.getByToken(token);
        Student student = studentUseCase.get(studentCode);
        studentUserAffiliationValidator.check(user,student);
        Answer answer = answerUseCase.getByCode(answerCode);
        Task task = taskUseCase.getByCode(taskCode);
        answerUseCase.saveAnswer(student,task,answer,time);

    }




}
