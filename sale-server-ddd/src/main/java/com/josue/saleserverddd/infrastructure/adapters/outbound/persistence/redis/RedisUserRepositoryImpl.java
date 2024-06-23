package com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.redis;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.domain.repository.UserRepository;
import com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.redis.entities.UserEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Profile("redis")
@Component
public class RedisUserRepositoryImpl implements UserRepository {

    private final RedisTemplate<String, UserEntity> redisTemplate;

    public RedisUserRepositoryImpl(RedisTemplate<String, UserEntity> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public User create(User user) {
        var entity = new UserEntity(user);
        redisTemplate.opsForValue()
                .set(entity.getId(), entity);
        return entity.toDomain();
    }

    @Override
    public User edit(User user) {
        var entity = new UserEntity(user);
        redisTemplate.opsForValue()
                .set(entity.getId(), entity);
        return entity.toDomain();
    }

    @Override
    public boolean delete(String id) {
        redisTemplate.opsForValue()
                .getAndDelete(id);
        return true;
    }

    @Override
    public Optional<User> getById(String id) {
        return Optional.ofNullable(
                        redisTemplate.opsForValue()
                                .get(id)
                )
                .map(UserEntity::toDomain);
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

}
