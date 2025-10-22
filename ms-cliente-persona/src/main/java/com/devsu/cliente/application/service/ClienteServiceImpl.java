package com.devsu.cliente.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devsu.cliente.application.dto.ClienteRequest;
import com.devsu.cliente.application.dto.ClienteResponse;
import com.devsu.cliente.infrastructure.persistence.entity.ClienteEntity;
import com.devsu.cliente.infrastructure.persistence.repository.ClienteRepository;
import com.devsu.cliente.shared.ApiResponse;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository repository;

	public ClienteServiceImpl(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public ClienteResponse guardar(ClienteRequest request) {
		ClienteEntity cliente = new ClienteEntity(request.getNombre(), request.getGenero(), request.getEdad(),
				request.getIdentificacion(), request.getDireccion(), request.getTelefono(), null,
				request.getContrasena(), request.isEstado());

		ClienteEntity nuevo = repository.save(cliente);
		ClienteResponse response = new ClienteResponse(nuevo.getId(), nuevo.getNombre(), nuevo.getGenero(),
				nuevo.getEdad(), nuevo.getIdentificacion(), nuevo.getDireccion(), nuevo.getTelefono(),
				nuevo.isEstado());
		return response;
	}

	@Override
	public List<ClienteResponse> obtenerTodos() {

		List<ClienteResponse> lista = repository.findAll().stream()
				.map(c -> new ClienteResponse(c.getId(), c.getNombre(), c.getGenero(), c.getEdad(),
						c.getIdentificacion(), c.getDireccion(), c.getTelefono(), c.isEstado()))
				.collect(Collectors.toList());
		return lista;
	}

	@Override
	public ResponseEntity<ApiResponse<ClienteResponse>> obtenerPorId(Long id) {

		ResponseEntity<ApiResponse<ClienteResponse>> clienteObtenido = repository.findById(id)
				.map(c -> ResponseEntity.ok(new ApiResponse<>("Cliente encontrado",
						new ClienteResponse(c.getId(), c.getNombre(), c.getGenero(), c.getEdad(), c.getIdentificacion(),
								c.getDireccion(), c.getTelefono(), c.isEstado()))))
				.orElse(ResponseEntity.status(404).body(new ApiResponse<>("Cliente no encontrado", null)));
		return clienteObtenido;
	}

	@Override
	public ResponseEntity<ApiResponse<ClienteResponse>> actualizar(Long id, ClienteRequest request) {
		Optional<ClienteEntity> clienteActualizado = repository.findById(id).map(c -> {
			c.setNombre(request.getNombre());
			c.setGenero(request.getGenero());
			c.setEdad(request.getEdad());
			c.setIdentificacion(request.getIdentificacion());
			c.setDireccion(request.getDireccion());
			c.setTelefono(request.getTelefono());
			c.setContrasena(request.getContrasena());
			c.setEstado(request.isEstado());
			return repository.save(c);
		});
		ResponseEntity<ApiResponse<ClienteResponse>> clienteActualizadoResponse = clienteActualizado
				.map(c -> ResponseEntity.ok(new ApiResponse<>("Cliente actualizado correctamente",
						new ClienteResponse(c.getId(), c.getNombre(), c.getGenero(), c.getEdad(), c.getIdentificacion(),
								c.getDireccion(), c.getTelefono(), c.isEstado()))))
				.orElse(ResponseEntity.status(404).body(new ApiResponse<>("Cliente no encontrado", null)));
		return clienteActualizadoResponse;
	}

	@Override
	public  ResponseEntity<ApiResponse<Void>> eliminar(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.ok(new ApiResponse<>("Cliente eliminado correctamente", null));
		}
		return ResponseEntity.status(404).body(new ApiResponse<>("Cliente no encontrado", null));
		
		
	}
}