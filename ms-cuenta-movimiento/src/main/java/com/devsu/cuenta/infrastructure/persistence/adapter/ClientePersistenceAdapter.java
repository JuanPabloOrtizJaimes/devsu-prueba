package com.devsu.cuenta.infrastructure.persistence.adapter;

import com.devsu.cuenta.application.port.out.ClientePersistencePort;
import com.devsu.cuenta.domain.model.Cliente;
import com.devsu.cuenta.infrastructure.persistence.mapper.ClientePersistenceMapper;
import com.devsu.cuenta.infrastructure.persistence.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientePersistenceAdapter implements ClientePersistencePort {

	private final ClienteRepository clienteRepository;
	private final ClientePersistenceMapper clientePersistenceMapper;

	public ClientePersistenceAdapter(ClienteRepository clienteRepository,
			ClientePersistenceMapper clientePersistenceMapper) {
		this.clienteRepository = clienteRepository;
		this.clientePersistenceMapper = clientePersistenceMapper;
	}

	@Override
	public Cliente save(Cliente cliente) {
		var entity = clientePersistenceMapper.toEntity(cliente);
		var savedEntity = clienteRepository.save(entity);
		return clientePersistenceMapper.toDomain(savedEntity);
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id).map(clientePersistenceMapper::toDomain);
	}

	@Override
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long clienteId) {

		return clienteRepository.existsById(clienteId);
	}
}
