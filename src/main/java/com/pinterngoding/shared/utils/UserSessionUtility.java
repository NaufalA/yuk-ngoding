package com.pinterngoding.shared.utils;

import com.pinterngoding.entity.User;

import java.util.Date;

public abstract class UserSessionUtility {
    private static User currentUser = null;
    private static Boolean isLoggedIn = false;
    private static Date lastLogin = null;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        if (currentUser != null) {
            isLoggedIn = true;
            lastLogin = new Date(System.currentTimeMillis());
        } else {
            isLoggedIn = false;
        }
        UserSessionUtility.currentUser = currentUser;
    }

    public static Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public static Date getLastLogin() {
        return lastLogin;
    }
}
