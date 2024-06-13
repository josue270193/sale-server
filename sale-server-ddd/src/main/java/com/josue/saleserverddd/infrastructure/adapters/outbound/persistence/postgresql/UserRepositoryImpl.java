package com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.postgresql;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.domain.repository.UserRepository;
import com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.postgresql.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final SpringUserRepository userRepository;

    public UserRepositoryImpl(SpringUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        var entity = new UserEntity(user);
        entity.setId(null);
        entity = userRepository.save(entity);
        return entity.toDomain();
    }

    @Override
    public User edit(User user) {
        var entity = new UserEntity(user);
        entity = userRepository.save(entity);
        return entity.toDomain();
    }

    @Override
    public boolean delete(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id)
                .map(UserEntity::toDomain);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserEntity::toDomain)
                .toList();
    }
}
