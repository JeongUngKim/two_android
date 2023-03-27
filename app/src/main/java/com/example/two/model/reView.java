package com.example.two.model;

public class reView {
    private int contentReviewId;

    private int contentReviewUserId;

    private int contentId;

    private String title;

    private String content;

    private float userRating;

    private String createdAt;

    private String updatedAt;

    private int likeCnt;

    public int getContentReviewId() {
        return contentReviewId;
    }

    public void setContentReviewId(int contentReviewId) {
        this.contentReviewId = contentReviewId;
    }

    public int getContentReviewUserId() {
        return contentReviewUserId;
    }

    public void setContentReviewUserId(int contentReviewUserId) {
        this.contentReviewUserId = contentReviewUserId;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }
}
