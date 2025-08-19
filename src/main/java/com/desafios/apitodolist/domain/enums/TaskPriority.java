package com.desafios.apitodolist.domain.enums;

public enum TaskPriority {
    URGENT,
    HIGH,
    MEDIUM,
    LOW;

    private String priority;

    TaskPriority() {
        this.priority = this.name().toLowerCase();
    }

    public String getPriority() {
        return priority;
    }

    public String getNumber() {
        return String.valueOf(this.ordinal() + 1);
    }
}
