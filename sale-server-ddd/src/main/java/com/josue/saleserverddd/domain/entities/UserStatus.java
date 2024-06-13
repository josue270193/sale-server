package com.josue.saleserverddd.domain.entities;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum UserStatus {

    ACTIVE,
    INACTIVE;

    public static Optional<UserStatus> getByName(String name) {
        if (StringUtils.hasText(name)) {
            return Arrays.stream(UserStatus.values())
                    .filter(status -> status.name().compareToIgnoreCase(name) == 0)
                    .findFirst();
        }
        return Optional.empty();
    }
}
