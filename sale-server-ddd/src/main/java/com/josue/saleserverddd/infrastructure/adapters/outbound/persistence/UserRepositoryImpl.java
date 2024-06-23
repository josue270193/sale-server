package com.josue.saleserverddd.infrastructure.adapters.outbound.persistence;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.domain.repository.UserRepository;
import com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.postgresql.PostgresqlUserRepositoryImpl;
import com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.redis.RedisUserRepositoryImpl;
import com.josue.saleserverddd.infrastructure.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Primary
@Component
public class UserRepositoryImpl implements UserRepository {

    private final ApplicationConfiguration appConfiguration;


    private PostgresqlUserRepositoryImpl postgresqlUserRepository;

    private RedisUserRepositoryImpl redisUserRepository;

    public UserRepositoryImpl(
            ApplicationConfiguration appConfiguration
    ) {
        this.appConfiguration = appConfiguration;
    }

    @Override
    public User create(User user) {
        var domain = user;
        if (appConfiguration.getDatabase().getPostgresql().getEnabled()) {
            domain = postgresqlUserRepository.create(user);
        }
        if (appConfiguration.getDatabase().getRedis().getEnabled()) {
            redisUserRepository.create(domain);
        }
        return domain;
    }

    @Override
    public User edit(User user) {
        var domain = user;
        if (appConfiguration.getDatabase().getPostgresql().getEnabled()) {
            domain = postgresqlUserRepository.edit(user);
        }
        if (appConfiguration.getDatabase().getRedis().getEnabled()) {
            redisUserRepository.edit(domain);
        }
        return domain;
    }

    @Override
    public boolean delete(String id) {
        if (appConfiguration.getDatabase().getPostgresql().getEnabled()) {
            postgresqlUserRepository.delete(id);
        }
        if (appConfiguration.getDatabase().getRedis().getEnabled()) {
            redisUserRepository.delete(id);
        }
        return true;
    }

    @Override
    public Optional<User> getById(String id) {
        if (appConfiguration.getDatabase().getPostgresql().getEnabled()) {
            return postgresqlUserRepository.getById(id);
        } else if (appConfiguration.getDatabase().getRedis().getEnabled()) {
            return redisUserRepository.getById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        if (appConfiguration.getDatabase().getPostgresql().getEnabled()) {
            return postgresqlUserRepository.getAll();
        } else if (appConfiguration.getDatabase().getRedis().getEnabled()) {
            return redisUserRepository.getAll();
        } else {
            return List.of();
        }
    }

    @Autowired(required = false)
    public void setPostgresqlUserRepository(PostgresqlUserRepositoryImpl postgresqlUserRepository) {
        this.postgresqlUserRepository = postgresqlUserRepository;
    }

    @Autowired(required = false)
    public void setRedisUserRepository(RedisUserRepositoryImpl redisUserRepository) {
        this.redisUserRepository = redisUserRepository;
    }
}
