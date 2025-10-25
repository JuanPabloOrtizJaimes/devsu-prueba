package com.devsu.cuenta.application.port.out;

import com.devsu.cuenta.domain.model.Cliente;

import java.util.Optional;

public interface ClientePersistencePort {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long clienteId);
}