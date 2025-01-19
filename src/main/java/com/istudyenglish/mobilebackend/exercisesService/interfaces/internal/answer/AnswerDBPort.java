package com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.answer;

import com.istudyenglish.mobilebackend.dictionary.domain.Language;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.*;

import java.util.List;
import java.util.UUID;

public interface AnswerDBPort {

    public List<Answer> get(List<UUID> uuidList);
    public Answer get(UUID answerUUID);
    public Answer get(String value, Language language) throws Exception;
    public List<Answer> getSimilarAnswers(Answer answer,int countSimilar);

    public List<Answer> getAll();

    void saveSimilarAnswers(SimilarAnswer similarAnswer);

    void updateSimilarAnswers(SimilarAnswer similarAnswer);

    void save(Answer answer);
}
