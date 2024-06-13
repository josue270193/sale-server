package com.josue.saleserverddd.domain.entities;

public record User(
        Long id,
        String name,
        String lastname,
        UserStatus status
) {
    public User {
        if (status == null) {
            status = UserStatus.ACTIVE;
        }
    }

    public User(String name, String lastname) {
        this(null, name, lastname, null);
    }
}
