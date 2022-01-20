package com.intelliatech.queryExtractor;

public class QuestionOptionExtractor {

    private int questionId;
    private String question;
    private String option;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public QuestionOptionExtractor(int questionId, String question, String option) {
        this.questionId = questionId;
        this.question = question;
        this.option = option;
    }

    public QuestionOptionExtractor() {
    }
}
