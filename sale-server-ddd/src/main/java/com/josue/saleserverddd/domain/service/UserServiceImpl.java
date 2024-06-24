package com.josue.saleserverddd.domain.service;

import com.josue.saleserverddd.application.ports.inbound.UserMapper;
import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.domain.exceptions.NotFoundEntityException;
import com.josue.saleserverddd.domain.repository.UserRepository;
import com.josue.saleserverddd.infrastructure.adapters.outbound.rest.openfeign.UserFeignClient;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFeignClient userFeignClient;

    public UserServiceImpl(UserRepository userRepository, UserFeignClient userFeignClient) {
        this.userRepository = userRepository;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public User create(User user) {
        user = new User(UUID.randomUUID().toString(), user.name(), user.lastname(), user.status());
        return userRepository.create(user);
    }

    @Override
    public User edit(User user) {
        return userRepository.edit(user);
    }

    @Override
    public boolean delete(String id) {
        return userRepository.delete(id);
    }

    @Override
    public User getById(String id) {
        return userRepository.getById(id)
                .orElseThrow(() -> new NotFoundEntityException("user", Map.of("id", id)));
    }

    @Override
    public List<User> getAll() {
        var externalUsers = userFeignClient.getUsers()
                .stream()
                .map(UserMapper::toDomain);

        var result = userRepository.getAll().stream();

        return Stream.concat(result, externalUsers)
                .toList();
    }
}
