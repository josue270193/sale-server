package com.josue.saleserverddd.infrastructure.adapters.outbound.rest.openfeign;

import com.josue.saleserver.model.UserResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserFeignClientFallback implements UserFeignClient {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<UserResponseDTO> getUsers() {
        log.info("Fallback: getUsers()");
        return new ArrayList<>();
    }
}
