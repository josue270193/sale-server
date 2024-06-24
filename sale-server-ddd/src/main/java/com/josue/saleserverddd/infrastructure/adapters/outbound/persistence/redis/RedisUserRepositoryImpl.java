package com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.redis;

import com.josue.saleserverddd.domain.entities.User;
import com.josue.saleserverddd.domain.repository.UserRepository;
import com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.redis.entities.UserEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Profile("redis")
@Component
public class RedisUserRepositoryImpl implements UserRepository {

    private final RedisTemplate<String, UserEntity> userRedisTemplate;

    public RedisUserRepositoryImpl(RedisTemplate<String, UserEntity> userRedisTemplate) {
        this.userRedisTemplate = userRedisTemplate;
    }

    @Override
    public User create(User user) {
        var entity = new UserEntity(user);
        userRedisTemplate.opsForValue()
                .set(entity.getId(), entity);
        return entity.toDomain();
    }

    @Override
    public User edit(User user) {
        var entity = new UserEntity(user);
        userRedisTemplate.opsForValue()
                .set(entity.getId(), entity);
        return entity.toDomain();
    }

    @Override
    public boolean delete(String id) {
        userRedisTemplate.opsForValue()
                .getAndDelete(id);
        return true;
    }

    @Override
    public Optional<User> getById(String id) {
        return Optional.ofNullable(
                        userRedisTemplate.opsForValue()
                                .get(id)
                )
                .map(UserEntity::toDomain);
    }

    @Override
    public List<User> getAll() {
        return Objects.requireNonNull(userRedisTemplate.keys("*"))
                .stream()
                .map(key -> userRedisTemplate.opsForValue().get(key))
                .filter(Objects::nonNull)
                .map(UserEntity::toDomain)
                .toList();
    }

}
