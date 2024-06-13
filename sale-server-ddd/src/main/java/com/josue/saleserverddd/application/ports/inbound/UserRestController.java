package com.josue.saleserverddd.application.ports.inbound;

import com.josue.saleserver.api.UserApi;
import com.josue.saleserver.model.UserResponseDTO;
import com.josue.saleserverddd.domain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController implements UserApi {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<UserResponseDTO> getUser() {
        var domain = userService.getById(1L);
        return ResponseEntity.ok(
            userMapper.toResponse(domain)
        );
    }
}
