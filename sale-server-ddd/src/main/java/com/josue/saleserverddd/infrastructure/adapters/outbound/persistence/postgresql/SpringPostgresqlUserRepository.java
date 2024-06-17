package com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.postgresql;

import com.josue.saleserverddd.infrastructure.adapters.outbound.persistence.postgresql.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringPostgresqlUserRepository extends JpaRepository<UserEntity, Long> {

}
