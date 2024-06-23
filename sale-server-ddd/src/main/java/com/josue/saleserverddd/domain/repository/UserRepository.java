package com.josue.saleserverddd.domain.repository;

import com.josue.saleserverddd.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User create(User user);

    User edit(User user);

    boolean delete(String id);

    Optional<User> getById(String id);

    List<User> getAll();

}
