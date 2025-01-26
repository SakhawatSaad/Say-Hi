package com.shsaad.sayhi.ui;

public class User {
    String userName,userId,userEmail,userphone,userCountry;

    public User(String userName, String userId, String userEmail, String userphone, String userCountry) {
        this.userName = userName;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userphone = userphone;
        this.userCountry = userCountry;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }
}
