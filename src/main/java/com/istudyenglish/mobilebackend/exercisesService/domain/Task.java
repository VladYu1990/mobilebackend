package com.istudyenglish.mobilebackend.exercisesService.domain;

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
    private UUID userUUID;

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
    private int countRightResponses;

    public Task(UUID exerciseUUID, UUID userUUID) {
        this.uuid = UUID.randomUUID();
        this.exerciseUUID = exerciseUUID;
        this.userUUID = userUUID;
        this.nextRepetition = Instant.now();
        this.lastRepetition = Instant.now();
        this.status = TaskStatus.NOT_READY;
        this.countRightResponses = 0;
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

    private void setCountRightResponses(int countRightResponses) {
        this.countRightResponses = Integer.max(countRightResponses,0);
        setStatusIfCountRightResponsesHasChanged();
    }


    private void setStatusIfCountRightResponsesHasChanged() {
        if (this.countRightResponses > 7 || status.equals(TaskStatus.STUDY)) {
            setStatus(TaskStatus.LEARNED);
        }
    }

    public void setStatusFromNotReadyToReady() {
        /*
        * Допустимо из вне получать только такой переход,
        * остальные переходы управляются логикой самой таски
         */
        if (this.status.equals(TaskStatus.NOT_READY)){
            this.status = TaskStatus.READY;
        }
    }

    public boolean checkUserAffiliation(UUID userUUID){
        return this.userUUID.equals(userUUID);
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
