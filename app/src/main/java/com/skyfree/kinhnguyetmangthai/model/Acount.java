package com.skyfree.kinhnguyetmangthai.model;

/**
 * Created by KienBeu on 3/10/2018.
 */

public class Acount {
    private String mPass, mConfirmPass, mQuestion, mAnswer;

    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
    }

    public String getmConfirmPass() {
        return mConfirmPass;
    }

    public void setmConfirmPass(String mConfirmPass) {
        this.mConfirmPass = mConfirmPass;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public Acount() {
    }

    public Acount(String mPass, String mConfirmPass, String mQuestion, String mAnswer) {
        this.mPass = mPass;
        this.mConfirmPass = mConfirmPass;
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
    }
}
