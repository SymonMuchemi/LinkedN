package com.symon.linkedn;


import java.util.HashMap;
import java.util.Map;

public class User {
    String name, email, userId;

    public User(String name, String email, String userId) {
        this.name = name;
        this.email = email;
        this.userId = userId;
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

    public Map<String, Object> toMap(){
        HashMap<String, Object> userDetails = new HashMap<>();
        userDetails.put("name", name);
        userDetails.put("email", email);
        userDetails.put("UserId", userId);

        return userDetails;
    }
}
