package com.josue.saleserverddd.infrastructure.adapters.outbound.persistence;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.domain.repository.UserRepository;
import com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.postgresql.PostgresqlUserRepositoryImpl;
import com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.redis.RedisUserRepositoryImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Primary
@Component
public class UserRepositoryImpl implements UserRepository {

    private final PostgresqlUserRepositoryImpl postgresqlUserRepository;
    private final RedisUserRepositoryImpl redisUserRepository;

    public UserRepositoryImpl(PostgresqlUserRepositoryImpl postgresqlUserRepository,
                              RedisUserRepositoryImpl redisUserRepository
    ) {
        this.postgresqlUserRepository = postgresqlUserRepository;
        this.redisUserRepository = redisUserRepository;
    }


    @Override
    public User create(User user) {
        var domain = postgresqlUserRepository.create(user);
        redisUserRepository.create(domain);
        return domain;
    }

    @Override
    public User edit(User user) {
        var domain = postgresqlUserRepository.edit(user);
        redisUserRepository.edit(domain);
        return domain;
    }

    @Override
    public boolean delete(Long id) {
        postgresqlUserRepository.delete(id);
        redisUserRepository.delete(id);
        return true;
    }

    @Override
    public Optional<User> getById(Long id) {
//        return redisUserRepository.getById(id);
        return postgresqlUserRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
//        return redisUserRepository.getAll();
        return postgresqlUserRepository.getAll();
    }
}
