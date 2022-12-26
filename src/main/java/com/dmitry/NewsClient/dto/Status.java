package com.dmitry.NewsClient.dto;

public enum Status {
    NEW,
    ACTIVE,
    PENDING,
    REJECTED,
    DEACTIVATED;

    public boolean isNew() {
        return this.equals(NEW);
    }

    public boolean isActive() {
        return this.equals(ACTIVE);
    }

    public boolean isPending() {
        return this.equals(PENDING);
    }

    public boolean isRejected() {
        return this.equals(REJECTED);
    }

    public boolean isDeactivated() {
        return this.equals(DEACTIVATED);
    }
}
