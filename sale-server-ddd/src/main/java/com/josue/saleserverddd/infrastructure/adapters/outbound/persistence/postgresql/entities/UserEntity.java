package com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.postgresql.entities;

import com.josue.saleserverddd.domain.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    public UserEntity(User user) {
        this.id = user.id();
        this.name = user.name();
        this.lastname = user.lastname();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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