package com.devsu.cliente.application.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.cliente.application.dto.ClienteRequest;
import com.devsu.cliente.application.dto.ClienteResponse;
import com.devsu.cliente.application.event.ClientData;
import com.devsu.cliente.application.event.ClientDeletedData;
import com.devsu.cliente.application.event.ClientEvent;
import com.devsu.cliente.application.event.RabbitMQEventPublisher;
import com.devsu.cliente.application.port.in.ClienteUseCase;
import com.devsu.cliente.application.port.out.ClientePersistencePort;
import com.devsu.cliente.domain.model.Cliente;
import com.devsu.cliente.infrastructure.exception.BusinessException;
import com.devsu.cliente.infrastructure.exception.ClienteNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteUseCase {

	private final ClientePersistencePort persistencePort;
	private final RabbitMQEventPublisher eventPublisher;

	public ClienteServiceImpl(ClientePersistencePort persistencePort, RabbitMQEventPublisher eventPublisher) {
		this.persistencePort = persistencePort;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public ClienteResponse guardar(ClienteRequest request) {
		if (persistencePort.findByIdentificacion(request.getIdentificacion()).isPresent()) {
			throw new BusinessException("Ya existe un cliente con la identificación " + request.getIdentificacion());
		}

		Cliente cliente = new Cliente(request.getNombre(), request.getGenero(), request.getEdad(),
				request.getIdentificacion(), request.getDireccion(), request.getTelefono(), null,
				request.getContrasena(), request.isEstado());

		Cliente nuevo = persistencePort.save(cliente);
		eventPublisher.publishClientCreatedEvent(new ClientEvent<>("client-created", java.time.LocalDateTime.now(), "ClientCreated", "ms-cliente-persona", new ClientData(nuevo.getId(), nuevo.getNombre())));

		return toResponse(nuevo);
	}

	@Override
	public List<ClienteResponse> obtenerTodos() {
		return persistencePort.findAll().stream().map(this::toResponse).collect(Collectors.toList());
	}

	@Override
	public ClienteResponse obtenerPorId(Long id) {
		Cliente cliente = persistencePort.findById(id)
				.orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));

		return toResponse(cliente);
	}

	@Override
	public ClienteResponse actualizar(Long id, ClienteRequest request) {
		Cliente cliente = persistencePort.findById(id)
				.orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));

		if (!cliente.getIdentificacion().equals(request.getIdentificacion())) {
			persistencePort.findByIdentificacion(request.getIdentificacion()).ifPresent(existing -> {
				throw new BusinessException(
						"Ya existe un cliente con la identificación " + request.getIdentificacion());
			});
		}

		cliente.setNombre(request.getNombre());
		cliente.setGenero(request.getGenero());
		cliente.setEdad(request.getEdad());
		cliente.setIdentificacion(request.getIdentificacion());
		cliente.setDireccion(request.getDireccion());
		cliente.setTelefono(request.getTelefono());
		cliente.setContrasena(request.getContrasena());
		cliente.setEstado(request.isEstado());

		Cliente actualizado = persistencePort.save(cliente);
		eventPublisher.publishClientUpdatedEvent(new ClientEvent<>("client-updated", java.time.LocalDateTime.now(), "ClientUpdated", "ms-cliente-persona", new ClientData(actualizado.getId(), actualizado.getNombre())));

		return toResponse(actualizado);
	}

	@Override
	public void eliminar(Long id) {
		if (!persistencePort.existsById(id)) {
			throw new ClienteNotFoundException("Cliente no encontrado con ID: " + id);
		}
		// Capture client ID before deletion
		eventPublisher.publishClientDeletedEvent(new ClientEvent<>("client-deleted", java.time.LocalDateTime.now(), "ClientDeleted", "ms-cliente-persona", new ClientDeletedData(id)));
		persistencePort.deleteById(id);
	}

	private ClienteResponse toResponse(Cliente cliente) {
		return new ClienteResponse(cliente.getId(), cliente.getNombre(), cliente.getGenero(), cliente.getEdad(),
				cliente.getIdentificacion(), cliente.getDireccion(), cliente.getTelefono(), cliente.isEstado());
	}
}