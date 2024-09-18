package com.istudyenglish.mobilebackend.application.studentAnswer;

import com.istudyenglish.mobilebackend.application.answer.AnswerChecker;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.answer.AnswerUseCaseImpl;
import com.istudyenglish.mobilebackend.application.student.StudentUseCaseImpl;
import com.istudyenglish.mobilebackend.application.task.TaskUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.port.in.studentAnswer.StudentAnswerAdapter;
import com.istudyenglish.mobilebackend.port.in.answer.AnswerUseCase;
import com.istudyenglish.mobilebackend.port.in.student.StudentUseCase;
import com.istudyenglish.mobilebackend.port.in.task.TaskUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class StudentAnswerAdapterImp implements StudentAnswerAdapter {
//todo проверь лишние объекты, конструктор и имплиментацию
    StudentUseCase studentUseCase;
    TaskUseCase taskUseCase;
    AnswerUseCase answerUseCase;
    AnswerChecker answerChecker;

    @Autowired
    public StudentAnswerAdapterImp(StudentUseCaseImpl studentUseCase, TaskUseCaseImpl taskUseCase, AnswerUseCaseImpl answerUseCase, AnswerChecker answerChecker) {
        this.studentUseCase = studentUseCase;
        this.taskUseCase = taskUseCase;
        this.answerUseCase = answerUseCase;
        this.answerChecker = answerChecker;
    }

    public void returnAnswer(String studentStrUUID, String taskStrUUID, String answerStrUUID, Instant time) throws CustomException {

        try {
            Student student;
            student = studentUseCase.get(UUID.fromString(studentStrUUID));
            Task task;
            task = taskUseCase.get(UUID.fromString(taskStrUUID));
            taskUseCase.checkStudentAffiliation(task, UUID.fromString(studentStrUUID));

            Answer answer;
            answer = answerUseCase.getByCode(answerStrUUID);

            boolean replyIsTrue;
            replyIsTrue = answerChecker.check(task,answer);

            answerUseCase.saveAnswer(task, answer, time,replyIsTrue);
        }
        catch (CustomException customException){
            throw customException;
        }
    }




}
