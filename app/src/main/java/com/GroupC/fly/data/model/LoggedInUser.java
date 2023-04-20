package com.GroupC.fly.data.model;

import com.GroupC.fly.data.Objects.User;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private final String email;
    private final String displayName;
    private User userInfo;

    public LoggedInUser(String userId, String displayName, User userInfo) {
        this.email = userId;
        this.displayName = displayName;
        this.userInfo = userInfo;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }
}