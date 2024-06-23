package com.josue.saleserverddd.domain.service;

import com.josue.saleserverddd.domain.entities.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User edit(User user);

    boolean delete(String id);

    User getById(String id);

    List<User> getAll();

}
