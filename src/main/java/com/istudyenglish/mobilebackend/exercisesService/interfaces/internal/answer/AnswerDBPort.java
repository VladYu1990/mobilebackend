package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.answer;

import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;

import java.util.List;
import java.util.UUID;

public interface AnswerDBPort {

    public List<Answer> get(List<UUID> uuidList);
}
