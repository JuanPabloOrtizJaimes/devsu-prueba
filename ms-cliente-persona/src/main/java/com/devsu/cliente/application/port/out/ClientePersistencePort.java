package com.devsu.cliente.application.port.out;

import java.util.List;
import java.util.Optional;

import com.devsu.cliente.domain.model.Cliente;

public interface ClientePersistencePort {

	Optional<Cliente> findByIdentificacion(String identificacion);

	Cliente save(Cliente cliente);

	List<Cliente> findAll();

	Optional<Cliente> findById(Long id);

	boolean existsById(Long id);

	void deleteById(Long id);

}
