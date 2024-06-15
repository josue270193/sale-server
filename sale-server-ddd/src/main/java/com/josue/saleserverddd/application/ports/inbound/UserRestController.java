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
    private final UserMapper userMapper;

    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @Override
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        var response = userService.getAll()
                .stream()
                .map(this.userMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UserResponseDTO> getUserById(Long userId) {
        var domain = userService.getById(userId);
        return ResponseEntity.ok(
                userMapper.toResponse(domain)
        );
    }

    @Override
    public ResponseEntity<UserResponseDTO> addUser(UserRequestDTO userRequestDTO) {
        var domain = userService.create(
                userMapper.toDomain(userRequestDTO)
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.toResponse(domain));
    }

    @Override
    public ResponseEntity<UserResponseDTO> editUser(Long userId, UserRequestDTO userRequestDTO) {
        var domain = userService.edit(
                userMapper.toDomain(userId, userRequestDTO)
        );
        return ResponseEntity
                .ok(userMapper.toResponse(domain));
    }
}
