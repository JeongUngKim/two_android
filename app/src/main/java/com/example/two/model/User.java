package com.example.two.model;

import java.io.Serializable;

public class User implements Serializable {


    private String name;

    private String nickname;

    private String email;
    private String password;

    private int gender;

    private int questionNum;

    private String answer;

    private String imgUrl;

    public User(String email, String password, String nickname, String imgUrl) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
    }


    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String nickname, String email, String password, int gender, int questionNum, String answer) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.questionNum = questionNum;
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public User(){

    }
}
