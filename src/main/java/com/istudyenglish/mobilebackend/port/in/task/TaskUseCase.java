package com.istudyenglish.mobilebackend.port.in.task;


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
Task getByUUID(UUID uuid);
Task getByCode(String taskCode);
Collection<Task> getNextTasks(Student student,int amount,Instant instant);
boolean checkAnswer(Task task, Answer answer);
void delete(Task task, Instant instant);
void deleteTasks(Collection<Task> taskCollection, Instant instant);
void processAnswer(UUID taskUUID, UUID answerUUID,Instant instant);

boolean checkStudentAffiliation(Task task,UUID studentUUID);


}
