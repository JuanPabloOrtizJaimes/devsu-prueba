package com.devsu.cuenta.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsu.cuenta.infrastructure.persistence.entity.CuentaEntity;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {
	boolean existsByNumeroCuenta(String numeroCuenta);

	Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);

	List<CuentaEntity> findByClienteId(Long clienteId);
}