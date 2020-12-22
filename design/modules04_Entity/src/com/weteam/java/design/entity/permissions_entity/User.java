package com.weteam.java.design.entity.permissions_entity;

public class User {

    private String userId;
    private String username;
    private String password;
    private int isAdministrator;

    public User(String userId, String username, String password, int isAdministrator) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.isAdministrator = isAdministrator;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(int isAdministrator) {
        this.isAdministrator = isAdministrator;
    }
}
