package com.josue.saleserverddd.application.ports.inbound;

import com.josue.saleserver.model.UserRequestDTO;
import com.josue.saleserver.model.UserResponseDTO;
import com.josue.saleserverddd.domain.entities.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {
    public UserResponseDTO toResponse(User domain) {
        return Optional.ofNullable(domain)
                .map(user -> new UserResponseDTO()
                        .id(user.id())
                        .name(user.name())
                        .lastname(user.lastname())
                )
                .orElse(null);
    }

    public User toDomain(UserRequestDTO userRequestDTO) {
        return new User(userRequestDTO.getName(), userRequestDTO.getLastname());
    }

    public User toDomain(String userId, UserRequestDTO userRequestDTO) {
        return new User(userId, userRequestDTO.getName(), userRequestDTO.getLastname());
    }
}
