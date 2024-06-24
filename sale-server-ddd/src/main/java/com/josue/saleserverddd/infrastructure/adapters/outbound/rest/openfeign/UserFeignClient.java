package com.josue.saleserverddd.infrastructure.adapters.outbound.rest.openfeign;

import com.josue.saleserver.model.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "userClient", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @RequestMapping(method = GET, value = "/user")
    List<UserResponseDTO> getUsers();

}