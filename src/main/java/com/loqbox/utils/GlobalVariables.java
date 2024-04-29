package com.loqbox.utils;

public class GlobalVariables {
    private static String firstName;

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        GlobalVariables.firstName = firstName;
    }
}
