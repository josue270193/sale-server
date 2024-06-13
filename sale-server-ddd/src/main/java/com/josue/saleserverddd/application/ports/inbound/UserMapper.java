package com.josue.saleserverddd.application.ports.inbound;

import com.josue.saleserver.model.UserResponseDTO;
import com.josue.saleserverddd.domain.entities.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {
    public UserResponseDTO toResponse(User domain) {
        return Optional.ofNullable(domain)
                .map(user -> {
                    return new UserResponseDTO()
                            .id(user.id() != null ? user.id().intValue() : null);
                })
                .orElse(null);
    }
}
