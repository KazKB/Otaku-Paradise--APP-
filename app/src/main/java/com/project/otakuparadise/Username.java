package com.project.otakuparadise;

public class Username {
    private static String username;

    public Username() {
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Username.username = username;
    }
}
