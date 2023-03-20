package com.example.two.model;

public class MessageItem {

    String nickname;
    String message;
    String time;
    String profileUrl;

    private int userId;


    public MessageItem(String nickname, String message, String time, String profileUrl) {
        this.nickname = nickname;
        this.message = message;
        this.time = time;
        this.profileUrl = profileUrl;
    }

    public MessageItem(String nickname, String message, String time, String profileUrl, int userId) {
        this.nickname = nickname;
        this.message = message;
        this.time = time;
        this.profileUrl = profileUrl;
        this.userId = userId;
    }

    //firebase DB에 객체로 값을 읽어올 때..
    //파라미터가 비어있는 생성자가 핑요함.
    public MessageItem() {
    }

    //Getter & Setter
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}