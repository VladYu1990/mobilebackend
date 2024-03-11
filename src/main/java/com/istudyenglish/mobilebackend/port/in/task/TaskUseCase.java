package com.istudyenglish.mobilebackend.port.in.task;


import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.domain.Education.Answer;
import com.istudyenglish.mobilebackend.domain.Education.Exercise;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

/**
 * список кейсов с тасками
 * создать одну таску
 * создать несколько тасок
 * найти таску по коду
 * найти следующую таску 1
 * найти следующие таски N
 * проверить ответ на правильность
 * апдейт таски при верном ответе
 * апдейт таски при не верном ответе
 * удалить таску
 * удалить таски
 */
public interface TaskUseCase {

void createTask(Student student, Exercise exercise, Instant instant);
void createTasks(Student student,Collection<Exercise> exerciseList, Instant instant);
Task get(UUID uuid);
Collection<Task> getNextTasks(Student student,int amount,Instant instant);
void checkStudentAffiliation(Task task,UUID studentUUID) throws CustomException;

void updateAfterReply(Task task,boolean trueReply,Instant time);


}
