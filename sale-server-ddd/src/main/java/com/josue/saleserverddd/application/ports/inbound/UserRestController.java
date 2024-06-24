package com.josue.saleserverddd.application.ports.inbound;

import com.josue.saleserver.api.UserApi;
import com.josue.saleserver.model.UserRequestDTO;
import com.josue.saleserver.model.UserResponseDTO;
import com.josue.saleserverddd.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController implements UserApi {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        var response = userService.getAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UserResponseDTO> getUserById(String userId) {
        var domain = userService.getById(userId);
        return ResponseEntity.ok(
                UserMapper.toResponse(domain)
        );
    }

    @Override
    public ResponseEntity<UserResponseDTO> addUser(UserRequestDTO userRequestDTO) {
        var domain = userService.create(
                UserMapper.toDomain(userRequestDTO)
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserMapper.toResponse(domain));
    }

    @Override
    public ResponseEntity<UserResponseDTO> editUser(String userId, UserRequestDTO userRequestDTO) {
        var domain = userService.edit(
                UserMapper.toDomain(userId, userRequestDTO)
        );
        return ResponseEntity
                .ok(UserMapper.toResponse(domain));
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userId) {
        var result = userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
