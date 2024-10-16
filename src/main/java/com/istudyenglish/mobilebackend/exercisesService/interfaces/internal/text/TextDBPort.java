package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.text;

import com.istudyenglish.mobilebackend.exercisesService.domain.Question;
import com.istudyenglish.mobilebackend.exercisesService.domain.Task;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;

import java.util.List;
import java.util.UUID;

public interface TextDBPort {

    public void create(Question question);
    public void create(Answer answer);

    public void update(Question question);
    public void update(Answer answer);
}
