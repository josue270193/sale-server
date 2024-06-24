package com.josue.saleserverddd.infrastructure.adapters.outbound.rest.openfeign;

import com.josue.saleserver.model.UserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public List<UserResponseDTO> getUsers() {
        System.out.println("Fallback: getUsers()");
        return new ArrayList<>();
    }
}
