package com.example.hocta.model;

public class CH {
    private int num;
    private String question, answer;

    public CH() {
    }

    public CH(int num, String question, String answer) {
        this.num = num;
        this.question = question;
        this.answer = answer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
