package com.example.two.model;

public class Chat {
   private int partyBoardId;

   private String service;

   private String title;

   private String createdAt;

   private int userId;

   private String serviceId;

   private int servicePassword;

   private String finishedAt;

    public int getPartyBoardId() {
        return partyBoardId;
    }

    public void setPartyBoardId(int partyBoardId) {
        this.partyBoardId = partyBoardId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public int getServicePassword() {
        return servicePassword;
    }

    public void setServicePassword(int servicePassword) {
        this.servicePassword = servicePassword;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }
}