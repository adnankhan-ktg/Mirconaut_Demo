package com.intelliatech.dto;

import java.util.List;

public class response {

    private Integer questionId;
    private String question;
    List<optionDto> list;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<optionDto> getList() {
        return list;
    }

    public void setList(List<optionDto> list) {
        this.list = list;
    }

    public response(Integer questionId, String question, List<optionDto> list) {
        this.questionId = questionId;
        this.question = question;
        this.list = list;
    }

    public response() {
    }
}
