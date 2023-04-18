package com.GroupC.fly.data.model;

import com.GroupC.fly.data.Objects.User;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private final String userId;
    private final String displayName;
    private User userInfo;

    public LoggedInUser(String userId, String displayName, User userInfo) {
        this.userId = userId;
        this.displayName = displayName;
        this.userInfo = userInfo;
    }

    public String getUserId() {
        return userId;
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