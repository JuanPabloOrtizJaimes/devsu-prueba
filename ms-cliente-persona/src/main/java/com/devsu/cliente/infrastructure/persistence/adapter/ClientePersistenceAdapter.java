package com.devsu.cliente.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.devsu.cliente.application.port.out.ClientePersistencePort;
import com.devsu.cliente.domain.model.Cliente;
import com.devsu.cliente.infrastructure.persistence.entity.ClienteEntity;
import com.devsu.cliente.infrastructure.persistence.repository.ClienteRepository;

@Component
public class ClientePersistenceAdapter implements ClientePersistencePort {

	private final ClienteRepository repository;

	public ClientePersistenceAdapter(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Cliente> findByIdentificacion(String identificacion) {
		return repository.findByIdentificacion(identificacion).map(this::toDomain);
	}

	@Override
	public Cliente save(Cliente cliente) {
		ClienteEntity clienteEntity = toEntity(cliente);
		return toDomain(repository.save(clienteEntity));
	}

	@Override
	public List<Cliente> findAll() {
		return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		return repository.findById(id).map(this::toDomain);
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	private ClienteEntity toEntity(Cliente cliente) {
		return new ClienteEntity(cliente.getNombre(), cliente.getGenero(), cliente.getEdad(),
				cliente.getIdentificacion(), cliente.getDireccion(), cliente.getTelefono(), cliente.getId(),
				cliente.getContrasena(), cliente.isEstado());
	}

	private Cliente toDomain(ClienteEntity clienteEntity) {
		return new Cliente(clienteEntity.getNombre(), clienteEntity.getGenero(), clienteEntity.getEdad(),
				clienteEntity.getIdentificacion(), clienteEntity.getDireccion(), clienteEntity.getTelefono(),
				clienteEntity.getId(), clienteEntity.getContrasena(), clienteEntity.isEstado());
	}

}
