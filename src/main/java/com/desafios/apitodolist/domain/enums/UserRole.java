package com.desafios.apitodolist.domain.enums;

public enum UserRole {
    USER,
    ADMIN;

    private String role;

    UserRole() {
        this.role = "ROLE_" + this.name();
    }

    public String getRole() {
        return role;
    }
}
