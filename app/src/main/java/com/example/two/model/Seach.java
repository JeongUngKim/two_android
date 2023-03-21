package com.example.two.model;

import java.util.List;

public class Seach {

    private int Id;

    private String title;

    private String genre;

    private String content;

    private String imgUrl;

    private List contentRating;

    private String createdYear;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List getContentRating() {
        return contentRating;
    }

    public void setContentRating(List contentRating) {
        this.contentRating = contentRating;
    }

    public String getCreatedYear() {
        return createdYear;
    }

    public void setCreatedYear(String createdYear) {
        this.createdYear = createdYear;
    }
}
