package com.istudyenglish.mobilebackend.tasksService.adapters;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TaskForView {
    private String taskUUID;
    private String question;
    private List<AnswerForView> answerForViewList;
}
