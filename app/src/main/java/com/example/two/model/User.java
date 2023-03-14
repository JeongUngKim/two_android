package com.example.two.model;

import java.io.Serializable;

public class User implements Serializable {

    private String email;
    private String password;
    private String nickname;

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
