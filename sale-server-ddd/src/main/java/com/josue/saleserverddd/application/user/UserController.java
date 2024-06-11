package com.josue.saleserverddd.application.user;

import com.josue.saleserver.api.UserApi;
import com.josue.saleserver.model.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UserController implements UserApi {

    @Override
    public ResponseEntity<UserResponseDTO> getUser() {
        return UserApi.super.getUser();
    }
}
