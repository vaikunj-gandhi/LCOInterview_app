package com.vaikunj.myinerview_app.Pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DsModel {

    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }



    private String Answer;


    public DsModel(String question, String Answer) {
        this.question = question;
        this.Answer = Answer;
    }


}

