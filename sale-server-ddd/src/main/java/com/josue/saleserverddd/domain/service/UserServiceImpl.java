package com.josue.saleserverddd.domain.service;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.domain.exceptions.NotFoundEntityException;
import com.josue.saleserverddd.domain.repository.UserRepository;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public User edit(User user) {
        return userRepository.edit(user);
    }

    @Override
    public boolean delete(Long id) {
        return userRepository.delete(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id)
                .orElseThrow(() -> new NotFoundEntityException("user", Map.of("id", id)));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
}
