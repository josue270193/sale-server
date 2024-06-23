package com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.redis.entities;

import com.josue.saleserverddd.domain.entities.User;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("user")
public class UserEntity implements Serializable {

    private String id;
    private String name;
    private String lastname;

    public UserEntity(User user) {
        this.id = user.id();
        this.name = user.name();
        this.lastname = user.lastname();
    }

    public UserEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public User toDomain() {
        return new User(
                getId(),
                getName(),
                getLastname(),
                null
        );
    }
}
