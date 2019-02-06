package com.example.siddhant.firebassign;

public class AssnModel {
    public String userName;
    public String userAge;

    public AssnModel() {
    }

    public AssnModel(String userName, String userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}