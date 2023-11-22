package com.symon.linkedn;


public class User {
    private String name, email, userId,gender,shortBio;
    Integer mobileNo;

    public User(String name,
                String email,
                String userId,
                Integer mobileNo,
                String gender,
                String shortBio
    ) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.mobileNo = mobileNo;
        this.gender = gender;
        this.shortBio = shortBio;
    }

    public User(){

    }

    public String getShortBio() {
        return shortBio;
    }

    public Integer getMobileNo() {
        return mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
