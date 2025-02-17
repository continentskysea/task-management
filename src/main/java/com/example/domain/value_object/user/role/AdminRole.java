package com.example.domain.value_object.user.role;

public class AdminRole {
    private static final String ROLE_NAME = "ADMIN";

    public AdminRole() {
        showAdminRole();
    }

    private String showAdminRole() {
        return ROLE_NAME;
    } 
}
