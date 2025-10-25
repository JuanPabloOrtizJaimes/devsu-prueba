package com.devsu.cuenta.application.port.out;

import com.devsu.cuenta.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaPersistencePort {

    Cuenta save(Cuenta cuenta);

    List<Cuenta> findAll();

    Optional<Cuenta> findById(Long id);

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    boolean existsByNumeroCuenta(String numeroCuenta);

    void deleteById(Long id);

    List<Cuenta> findByClienteId(Long clienteId);
}
