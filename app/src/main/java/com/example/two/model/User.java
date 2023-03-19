package com.example.two.model;

import java.io.Serializable;

public class User implements Serializable {


    private String name;

    private String nickname;

    private String userEmail;
    private String password;

    private int gender;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int questionNum;

    private String questionAnswer;

    private String profileImgUrl;

    private String createdAt;

    public User(String userEmail, String password, String nickname, String profileImgUrl) {
        this.userEmail = userEmail;
        this.password = password;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
    }


    public User(String userEmail, String password, String nickname) {
        this.userEmail = userEmail;
        this.password = password;
        this.nickname = nickname;
    }

    public User(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public User(String name, String nickname, String userEmail, String password, int gender, int questionNum, String questionAnswer) {
        this.name = name;
        this.nickname = nickname;
        this.userEmail = userEmail;
        this.password = password;
        this.gender = gender;
        this.questionNum = questionNum;
        this.questionAnswer = questionAnswer;
    }

    public User(String name, int questionNum, String questionAnswer) {
        this.name = name;
        this.questionNum = questionNum;
        this.questionAnswer = questionAnswer;
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
