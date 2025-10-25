package com.devsu.cuenta.infrastructure.persistence.repository;

import com.devsu.cuenta.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
