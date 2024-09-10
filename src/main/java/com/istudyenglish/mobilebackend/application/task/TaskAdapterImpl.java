package com.istudyenglish.mobilebackend.application.task;

import com.istudyenglish.mobilebackend.adapter.Word.RequestForNeTasksdDAO;
import com.istudyenglish.mobilebackend.adapter.task.ConvertorToTaskDTO;
import com.istudyenglish.mobilebackend.adapter.task.TaskDTO;
import com.istudyenglish.mobilebackend.application.CustomException.CustomException;
import com.istudyenglish.mobilebackend.application.word.WordUseCaseImpl;
import com.istudyenglish.mobilebackend.domain.Education.Exercise;
import com.istudyenglish.mobilebackend.domain.Education.Student;
import com.istudyenglish.mobilebackend.domain.Education.Task.Task;
import com.istudyenglish.mobilebackend.dictionary.domain.Word;
import com.istudyenglish.mobilebackend.port.in.exercise.ExerciseUseCase;
import com.istudyenglish.mobilebackend.port.in.student.StudentUseCase;
import com.istudyenglish.mobilebackend.port.in.task.TaskAdapter;
import com.istudyenglish.mobilebackend.port.in.task.TaskUseCase;
import com.istudyenglish.mobilebackend.port.in.word.WordUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Component
public class TaskAdapterImpl implements TaskAdapter {
//todo проверь лишние объекты, конструктор и имплиментацию
    StudentUseCase studentUseCase;
    TaskUseCase taskUseCase;
    ExerciseUseCase exerciseUseCase;
    ConvertorToTaskDTO convertorToTaskDTO;
    WordUseCase wordUseCase;
    RequestForNeTasksdDAO requestForNeTasksdDAO;

    @Autowired
    public TaskAdapterImpl(StudentUseCase studentUseCase, TaskUseCase taskUseCase, ExerciseUseCase exerciseUseCase, ConvertorToTaskDTO convertorToTaskDTO, WordUseCaseImpl wordUseCaseImp,RequestForNeTasksdDAO requestForNeTasksdDAO) {
        this.studentUseCase = studentUseCase;
        this.taskUseCase = taskUseCase;
        this.exerciseUseCase = exerciseUseCase;
        this.convertorToTaskDTO = convertorToTaskDTO;
        this.wordUseCase = wordUseCaseImp;
        this.requestForNeTasksdDAO = requestForNeTasksdDAO;
    }

    public void create(String studentUUid, List<String> strings) throws CustomException {
        Student student = studentUseCase.get(UUID.fromString(studentUUid));
        List<Word> words = wordUseCase.get(strings);
        checkCreated(student,strings,words);
        List<Exercise> exercises= exerciseUseCase.get(strings);
        taskUseCase.createTasks(student,exercises);
    }

    private void checkCreated(Student student, List<String> strings, List<Word> words) {
        if(words.size() == strings.size()){
            return;
        }
        HashSet<String> hashSet = new HashSet<>(strings);
        for(Word word:words){
                hashSet.remove(word.getEng());
                hashSet.remove(word.getRus());
            }
        saveRequestForNeTasksdDAO(hashSet.stream().toList(),student);
    }


    private void saveRequestForNeTasksdDAO(List<String> wordsNotFound,Student student){
        requestForNeTasksdDAO.save(wordsNotFound,student);

    }





    public Collection<TaskDTO> getNextTask(String studentStrUUID, int amountTasks, int amountAnswer, Instant time) throws CustomException{

        Student student;
        student = studentUseCase.get(UUID.fromString(studentStrUUID));
        Collection<Task> tasks;
        tasks = taskUseCase.getNextTasks(student,amountTasks,time);
        return convertorToTaskDTO.convert(tasks,amountAnswer);
    }

    private List<Word> getWord(List<String> strings){
        List<Word>  words = new ArrayList<>();

        return words;
    }




}
