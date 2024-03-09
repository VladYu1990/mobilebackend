package com.istudyenglish.mobilebackend.domain.Education.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

@Getter
@Setter
@AllArgsConstructor
@Builder
/**
 * Хранит информации об изучении/обучении конкретным студентом конкретной фразы/слова и тд
 */
public class Task implements Comparable<Task> {

    /**
     * Уникальный код
     */
    private UUID uuid;
    /**
     * Связь с исходным упражнением
     */
    private UUID exerciseUUID;
    /**
     * Связь задания со студентом
     */
    private UUID studentUUID;
    /**
     * Время, после которого можно повторить задание
     */
    private Instant nextRepetition;
    /**
     * Время, когда последний раз повторяли задание
     */
    private Instant lastRepetition;
    /**
     * Статус задания
     */
    private TaskStatus status;
    /**
     * Количество верных ответов ПОДРЯД, не отрицательное число
     */
    private int countRightResponses = 0;

    public void setCountRightResponses(int countRightResponses) {
        this.countRightResponses = Integer.max(countRightResponses,0);
        setStatusIfCountRightResponsesHasChanged();
    }

    public void updateIfAnswerIsTrue(Instant instant) {
        setCountRightResponses(this.countRightResponses + 1);
        setLastRepetition(instant);
        setNextRepetition(instant.plus(this.countRightResponses * 2, DAYS));
    }

    public void updateIfAnswerIsFalse(Instant instant) {
        setCountRightResponses(0);
        setLastRepetition(instant);
        setNextRepetition(instant.plus(1, DAYS));
    }


    private void setStatusIfCountRightResponsesHasChanged() {
        if (status.equals(TaskStatus.READY)) {
            setStatus(TaskStatus.STUDY);
        }
        if (this.countRightResponses > 7 || status.equals(TaskStatus.STUDY)) {
            setStatus(TaskStatus.LEARNED);
        }
    }

    public void setStatus(TaskStatus status) {
        /*
        * Допустимо из вне получать только такой переход,
        * остальные переходы управляются логикой самой таски
         */
        if ( this.status.equals(TaskStatus.NOT_READY)||
                status.equals(TaskStatus.READY)) {
            this.status = status;
        }
    }

    public boolean checkStudentAffiliation(UUID studentUUID){
        if (this.getStudentUUID().toString() == studentUUID.toString()){
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public int compareTo(Task o) {
        if (this.nextRepetition.isBefore(o.nextRepetition)) {
            return 1;
        } else {
            return -1;
        }
    }
}
