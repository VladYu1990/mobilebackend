package com.istudyenglish.mobilebackend.exercisesService.interfaces.external;

import com.istudyenglish.mobilebackend.configuration.Configuration;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.Answer;
import com.istudyenglish.mobilebackend.exercisesService.domain.answer.SimilarAnswer;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.answer.AnswerDAO;
import com.istudyenglish.mobilebackend.exercisesService.interfaces.internal.answer.AnswerDBPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SimilarAnswerUseCasesImp implements SimilarAnswerUseCases {
    private AnswerDBPort answerDBPort;


    @Autowired
    public SimilarAnswerUseCasesImp(AnswerDAO answerDBPort) {
        this.answerDBPort = answerDBPort;
    }


    @Override
    public void create(UUID answer, int count) {

    }

    @Override
    public void countUpForAnswer(UUID answerUUID) {

    }

    @Override
    public List<Answer> getSimilarByAnswerUUID(Answer answer,int maxCountSimilarAnswer) {
        return answerDBPort.getSimilarAnswers(answer,maxCountSimilarAnswer);
    }

    @Override
    public void updateAll() {
        List<Answer> answerList = getAllAnswer();
        for (Answer answer1 : answerList) {
            List<SimilarAnswer> similarAnswerList = createSimilarAnswerForAnswer(answer1,answerList);
            sort(similarAnswerList);
            trimToCountFromConfig(similarAnswerList);
            saveSimilarAnswer(similarAnswerList);
            }
    }


    @Override
    public void update(Answer answer) {
        List<Answer> answerList = getAllAnswer();
        List<SimilarAnswer> similarAnswerList = createSimilarAnswerForAnswer(answer,answerList);
        sort(similarAnswerList);
        trimToCountFromConfig(similarAnswerList);
        saveSimilarAnswer(similarAnswerList);
    }

    private List<Answer> getAllAnswer(){
        return answerDBPort.getAll();
    }

    private List<SimilarAnswer> createSimilarAnswerForAnswer(Answer answer1,List<Answer> allAnswers){
        List<SimilarAnswer> similarAnswerList = new ArrayList<>();
        for(Answer answer2:allAnswers){
            if (answer1 != answer2 || answer1.getLanguage().equals(answer2.getLanguage())) {
                similarAnswerList.add(new SimilarAnswer(answer1, answer2));
            }
        }
        return similarAnswerList;
    }

    private void sort(List<SimilarAnswer> similarAnswerList){
        Collections.sort(similarAnswerList);
    }

    private void trimToCountFromConfig(List<SimilarAnswer> similarAnswerList){
        int k = Configuration.maxSimilarAnswersInDB;

        if (k > similarAnswerList.size()) {
            k = similarAnswerList.size();
        }
    }

    private void saveSimilarAnswer(List<SimilarAnswer> similarAnswerList){
        for (SimilarAnswer similarAnswer:similarAnswerList) {
            try{
                answerDBPort.saveSimilarAnswers(similarAnswer);
            }
            catch (Exception e){
                answerDBPort.updateSimilarAnswers(similarAnswer);
            }
        }

    }
}
