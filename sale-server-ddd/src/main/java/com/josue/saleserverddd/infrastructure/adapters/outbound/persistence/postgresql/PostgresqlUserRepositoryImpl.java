package com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.postgresql;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.domain.repository.UserRepository;
import com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.postgresql.entities.UserEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Profile("postgresql")
@Component
public class PostgresqlUserRepositoryImpl implements UserRepository {

    private final SpringPostgresqlUserRepository userRepository;

    public PostgresqlUserRepositoryImpl(SpringPostgresqlUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        var entity = new UserEntity(user);
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
    public boolean delete(String id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<User> getById(String id) {
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
