package com.ty;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String userId;
    
    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name;
    }
}
