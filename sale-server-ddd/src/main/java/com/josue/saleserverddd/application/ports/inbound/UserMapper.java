package com.josue.saleserverddd.application.ports.inbound;

import com.josue.saleserver.model.UserRequestDTO;
import com.josue.saleserver.model.UserResponseDTO;
import com.josue.saleserverddd.domain.entities.User;

import java.util.Optional;

public class UserMapper {

    private UserMapper() {

    }

    public static UserResponseDTO toResponse(User domain) {
        return Optional.ofNullable(domain)
                .map(user -> new UserResponseDTO()
                        .id(user.id())
                        .name(user.name())
                        .lastname(user.lastname())
                )
                .orElse(null);
    }

    public static User toDomain(UserRequestDTO dto) {
        return new User(dto.getName(), dto.getLastname());
    }

    public static User toDomain(String userId, UserRequestDTO dto) {
        return new User(userId, dto.getName(), dto.getLastname());
    }

    public static User toDomain(UserResponseDTO dto) {
        return new User(dto.getId(), dto.getName(), dto.getLastname());
    }
}
